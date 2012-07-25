package org.github.rabid_fish.common;

import java.util.List;

import org.github.rabid_fish.service.util.ServiceEntity;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

public class CommonCrudOperations {

	private final String VIEW_FOR_LIST;
	private final String VIEW_FOR_EDIT;
	private final String REDIRECT_TO_LIST;

	public CommonCrudOperations(String modelName) {
		this.VIEW_FOR_LIST = modelName + "/" + modelName + "List";
		this.VIEW_FOR_EDIT = modelName + "/" + modelName + "Edit";
		this.REDIRECT_TO_LIST = "redirect:list";
	}

	public ModelAndView list(ModelMap modelMap, CommonModelService<?> modelService) {

		List<?> list = modelService.list();
		if (list.size() == 0) {
			modelMap.addAttribute("message", "No XXXXX exist yet, please create one");
		}

		return new ModelAndView(VIEW_FOR_LIST, "list", list);
	}

	public String create(Model model, Object item) {
		model.addAttribute(item);
		return VIEW_FOR_EDIT;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String createSave(BindingResult result, CommonModelService modelService, ServiceEntity entity) {

		if (result.hasErrors()) {
			return VIEW_FOR_EDIT;
		}

		modelService.insert(entity);
		return REDIRECT_TO_LIST;
	}

	@SuppressWarnings({ "rawtypes" })
	public String update(Model model, Long id, CommonModelService modelService) {
		ServiceEntity entity = (ServiceEntity) modelService.read(id);
		model.addAttribute(entity);
		return VIEW_FOR_EDIT;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String updateSave(BindingResult result, CommonModelService modelService, ServiceEntity entity) {

		if (result.hasErrors()) {
			return VIEW_FOR_EDIT;
		}

		modelService.update(entity);
		return REDIRECT_TO_LIST;
	}
}