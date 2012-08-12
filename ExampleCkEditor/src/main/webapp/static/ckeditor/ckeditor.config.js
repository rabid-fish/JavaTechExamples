/*
Copyright (c) 2003-2012, CKSource - Frederico Knabben. All rights reserved.
For licensing, see LICENSE.html or http://ckeditor.com/license
*/

// It is just a helper file that displays a red message about browser compatibility
// at the top of the samples (if incompatible browser is detected).

if ( window.CKEDITOR )
{
	(function()
	{
		var showCompatibilityMsg = function()
		{
			var env = CKEDITOR.env;

			var html = '<p><strong>Your browser is not compatible with CKEditor.</strong>';

			var browsers =
			{
				gecko : 'Firefox 2.0',
				ie : 'Internet Explorer 6.0',
				opera : 'Opera 9.5',
				webkit : 'Safari 3.0'
			};

			var alsoBrowsers = '';

			for ( var key in env )
			{
				if ( browsers[ key ] )
				{
					if ( env[key] )
						html += ' CKEditor is compatible with ' + browsers[ key ] + ' or higher.';
					else
						alsoBrowsers += browsers[ key ] + '+, ';
				}
			}

			alsoBrowsers = alsoBrowsers.replace( /\+,([^,]+), $/, '+ and $1' );

			html += ' It is also compatible with ' + alsoBrowsers + '.';

			html += '</p><p>With non compatible browsers, you should still be able to see and edit the contents (HTML) in a plain text field.</p>';

			var alertsEl = document.getElementById( 'alerts' );
			alertsEl && ( alertsEl.innerHTML = html );
		};

		var onload = function()
		{
			// Show a friendly compatibility message as soon as the page is loaded,
			// for those browsers that are not compatible with CKEditor.
			if ( !CKEDITOR.env.isCompatible )
				showCompatibilityMsg();
		};

		// Register the onload listener.
		if ( window.addEventListener )
			window.addEventListener( 'load', onload, false );
		else if ( window.attachEvent )
			window.attachEvent( 'onload', onload );
	})();
}

CKEDITOR.editorConfig = function( config )
{
	config.toolbar = 'CustomToolbar';
 
	config.toolbar_CustomToolbar =
	[
		{ name: 'document', items : [ 'Preview','Maximize','Print' ] },
		{ name: 'clipboard', items : [ 'Cut','Copy','Paste','PasteText','PasteFromWord','-','Undo','Redo' ] },
		{ name: 'insert', items : [ 'Link','Unlink','-','Image','Table','-','About' ] },
		'/',
		{ name: 'styles', items : [ 'Font','FontSize' ] },
		{ name: 'basicstyles', items : [ 'TextColor','BGColor','-','Bold','Italic','Underline','Strike','Subscript','Superscript','-','RemoveFormat' ] },
		{ name: 'paragraph', items : [ 'NumberedList','BulletedList','-','Outdent','Indent','-','Blockquote', '-','JustifyLeft','JustifyCenter','JustifyRight','JustifyBlock','-','BidiLtr','BidiRtl' ] },
		
	];

	// This list mirrors ckeditor.pack
	config.removePlugins = 
	  'sourcearea,' +
	  'newpage,' +
	  'templates,' +
	  'find,' +
	  'scayt,' +
	  'forms,' +
	  'div,' +
	  'save,' +
	  'filebrowser,' +
	  'flash,' +
	  'horizontalrule,' +
	  'smiley,' +
	  'specialchar,' +
	  'pagebreak,' +
	  'iframe,' +
	  'liststyle,' +
	  'stylescombo,' +
	  'format,' +
	  'showblocks';
	
//	config.contentsCss = 'sample.css';
	config.contentsCss = '';
	
	config.skin = 'kama,kama/';
};
