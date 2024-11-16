package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

public class SidebarPageConfigChapter2Impl implements SidebarPageConfig{
    HashMap<String,SidebarPage> pageMap = new LinkedHashMap<String,SidebarPage>();
    public SidebarPageConfigChapter2Impl(){
        pageMap.put("fn1",new SidebarPage("zk","ZK","/img/grobe.jpeg","http://www.zkoss.org/"));
        pageMap.put("fn2",new SidebarPage("demo","ZK Demo","/img/pc.png","http://www.zkoss.org/zkdemo"));
        pageMap.put("fn3",new SidebarPage("devref","ZK Developer Reference","/img/list.png","http://books.zkoss.org/wiki/ZK_Developer's_Reference"));
    }


    // Get a list of all sidebar pages
    @Override
    public List<SidebarPage> getPages() {
        return new ArrayList<>(pageMap.values());
    }

    // Get a specific sidebar page by ID
    @Override
    public SidebarPage getPage(String id) {
        return pageMap.get(id);
    }

    // Add a new sidebar page
    public void addPage(String id, SidebarPage page) {
        pageMap.put(id, page);
    }

    // Remove a sidebar page by ID
    public void removePage(String id) {
        pageMap.remove(id);
    }

    // Check if a page exists by ID
    public boolean containsPage(String id) {
        return pageMap.containsKey(id);
    }
}
