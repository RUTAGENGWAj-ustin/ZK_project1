package org.example;

import java.util.List;

public interface SidebarPageConfig {
    List<SidebarPage> getPages();      // Returns the list of all sidebar pages
    SidebarPage getPage(String id);
}
