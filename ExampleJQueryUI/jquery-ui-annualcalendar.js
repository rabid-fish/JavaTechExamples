
// WARNING: this is a hack! Intercept Datepicker's _inlineDatepicker function so
// we can override the 'width' style on the toplevel div which breaks responsive
// design.
$.datepicker._inlineDatepicker_original = $.datepicker._inlineDatepicker;
$.datepicker._inlineDatepicker = function(target, inst) {
	$.datepicker._inlineDatepicker_original(target, inst);
	
	if (typeof inst.settings.disableDefaultWidth != 'undefined' &&
		inst.settings.disableDefaultWidth == true) {
		inst.dpDiv.css("width","");
	}
};

// WARNING: this is a hack! Intercept Datepicker's _generateHTML function so
// we can set the month and year separate from defaulDate (whatever today is).
// In this way the first day of the generated calendar will not be 
// unnecessarily highlighted.
$.datepicker._generateHTML_original = $.datepicker._generateHTML;
$.datepicker._generateHTML = function(inst) {
	if (typeof inst.settings.drawMonth != 'undefined') {
		inst.drawMonth = inst.settings.drawMonth;
	}
	if (typeof inst.settings.drawYear != 'undefined') {
		inst.drawYear = inst.settings.drawYear;
	}
	
	var html = $.datepicker._generateHTML_original(inst);
	return html;
};

// WARNING: this is a hack! Intercept Datepicker's _attachHandlers function so
// we can 1) find any months that contain 5 rows and insert a ghost 6th row to
// keep all months the same height, and 2) remove the stock 'prev' and 'next'
// buttons.
$.datepicker._attachHandlers_original = $.datepicker._attachHandlers;
$.datepicker._attachHandlers = function(inst) {

	var htmlEmptyCell = " \n\
		<td class='ui-datepicker-other-month ui-datepicker-unselectable ui-state-disabled'> \n\
			<span class='ui-state-default'>&nbsp;</span> \n\
		</td> \n\
	";
	
	var htmlEmptyRow = '<tr>' + htmlEmptyCell + htmlEmptyCell + htmlEmptyCell + 
		htmlEmptyCell + htmlEmptyCell + htmlEmptyCell + htmlEmptyCell + '</tr>';

	inst.dpDiv.find('table.ui-datepicker-calendar tbody').each(function(index, element) {
		var length = $(element).find('tr').length;
		if (length == 5) {
			$(element).append(htmlEmptyRow);
		}
	});
	
	inst.dpDiv.find('.ui-datepicker-prev,.ui-datepicker-next').remove();
	
	$.datepicker._attachHandlers_original(inst);
	
	if (inst.settings.dateValues != 'undefined') {
		inst.dpDiv
			.find('table.ui-datepicker-calendar tbody td')
			.each(function(index, element) {
			
			var anchor = $(element).find('a');
			var year = this.getAttribute("data-year");
			var month = this.getAttribute("data-month");
			var day = anchor.html();
			var date = new Date(year, month, day);
			var formattedDate = $.datepicker.formatDate(inst.settings.dateFormat, date);
			
			var value = inst.settings.dateValues[formattedDate];
			if (typeof value != 'undefined') {
				$(element).attr('title', value);
				anchor.addClass(inst.settings.dateHighlightCss);
				anchor.removeClass('ui-state-default');
			}
		});
	}
	
	$('table.ui-datepicker-calendar tbody td[title]').qtip();
};

// WARNING: this is a hack! Intercept Datepicker's _selectDate function so
// we can disable the feature if need be.
$.datepicker._selectDate_original = $.datepicker._selectDate;
$.datepicker._selectDate = function(id, dateStr) {

	var target = $(id);
	var inst = this._getInst(target[0]);
	if (typeof inst.settings.disableOnSelect != 'undefined' &&
		inst.settings.disableOnSelect == true) {
		return;
	}
	
	$.datepicker._selectDate_original(id, dateStr);
};

// The function below is the constructor to call with the id of the html
// element to attach an AnnualCalendar datepicker to.
function AnnualCalendar(id, passedInSettings) {
	
	var settings = {};
	passedInSettings = (typeof passedInSettings === 'undefined') ? {} : passedInSettings;
	
	var formatDate = function(format, date) {
		return $.datepicker.formatDate(format, date);
	};
	
	var defaultSettings = {
		display: 'inline',
		drawMonth: 0,
		drawYear: Number(formatDate('yy', new Date())),
		
		// Force the format to match Java's MM/dd/yyyy 
		// note: datepicker's date format string is not 
		// the same as Java's
		dateFormat: 'mm/dd/yy',
		
		dateArray: [],
		dateFilter: null,
		datesToHighlight: [],
		dateValues: [],
		dateHighlightCss: 'ui-annualcalendar-highlight',
		mapDatesToValues: null,
		
		buttonCss: 'ui-annualcalendar-button',
		showOtherMonths: true,
		numberOfMonths: 12,
		disableOnSelect: true,
		disableDefaultWidth: true
	};
	
	var getFilteredDateArray = function(dateArray, dateFilter) {
		
		if (dateFilter == null) {
			return dateArray;
		}
		
		var buffer = [];
		for (var key in dateArray) {
			var date = dateFilter(dateArray[key]);
			if (date) {
				buffer.push(date);
			}
		}
		
		return buffer;
	};
	
	var incrementYear = function(years) {
		settings.drawYear = settings.drawYear + years;
		drawCalendar();
	};
	
	var drawCalendar = function() {
		$('#' + id).empty();
		$('#' + id).removeClass('hasDatepicker');
		drawTitlebar();
		drawBody();
	}
	
	var drawTitlebar = function() {
		
		var titlebar = " \n\
			<h2 id='" + id + "'> \n\
			<span class='" + settings.buttonCss + "'><a id='" + id + "Previous'>&lt; Prev</a></span> \n\
			" + settings.drawYear + " \n\
			<span class='" + settings.buttonCss + "'><a id='" + id + "Next'>Next &gt;</a></span> \n\
			</h2> \n\
		";
		
		$('#' + id).prepend(titlebar);
		$('#' + id + 'Previous').click(function() { incrementYear(-1); });
		$('#' + id + 'Next').click(function() { incrementYear(1); });
	};
	
	var drawBody = function() {
		$('#' + id).datepicker(settings);
	};
	
	var initialize = function () {
		settings = $.extend(settings, defaultSettings, passedInSettings);
		settings.datesToHighlight = getFilteredDateArray(settings.dateArray, settings.dateFilter);
		settings.dateValues = settings.mapDatesToValues(settings.dateArray);
		drawCalendar();
	}
	
	initialize();
}

