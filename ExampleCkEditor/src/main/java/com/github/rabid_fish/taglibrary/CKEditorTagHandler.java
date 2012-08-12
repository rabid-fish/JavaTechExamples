package com.github.rabid_fish.taglibrary;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

public class CKEditorTagHandler extends TagSupport {

	private static final long serialVersionUID = 1L;

	private String id;
	
	@Override
    public int doStartTag() throws JspException {
         
        try {
            JspWriter out = pageContext.getOut();
            out.println(getId());
 
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return SKIP_BODY;
    }
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
}
