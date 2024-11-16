package org.example;

import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.zk.ui.Executions;

import java.util.List;

public class SidebarViewModel {
    private final SidebarPageConfig pageConfig = new SidebarPageConfigChapter2Impl();

    public List<SidebarPage> getSidebarPages(){
        return  pageConfig.getPages();
    }
    @Command
    public  void navigate(@BindingParam("page") SidebarPage page){
        Executions.getCurrent().sendRedirect(page.getUrl());
    }
}
