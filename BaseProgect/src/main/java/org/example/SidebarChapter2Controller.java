package org.example;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.event.SerializableEventListener;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Image;
import org.zkoss.zul.Label;
import org.zkoss.zul.Row;
import org.zkoss.zul.Rows;

public class SidebarChapter2Controller extends SelectorComposer<Component> {

    @Wire
    private Grid sidebar;  // Sidebar grid

    // SidebarPageConfig instance to get sidebar pages
    private final SidebarPageConfig pageConfig = new SidebarPageConfigChapter2Impl();

    @Override
    public void doAfterCompose(Component comp) throws Exception {
        super.doAfterCompose(comp);

        // Initialize the rows inside the sidebar grid
        Rows rows = sidebar.getRows();

        // Loop through each sidebar page and construct rows
        for (SidebarPage page : pageConfig.getPages()) {
            Row row = constructSidebarRow(page.getName(),page.getImage() , page.getPathOfIcon(), page.getUrl() );
            rows.appendChild(row);
        }
    }

    // Helper method to construct a Row for each sidebar page
    private Row constructSidebarRow(String name, String label, String image, final String locationUri) {
        // Create a new row and image
        Row row = new Row();
        Image images = new Image(image);
        Label lab = new Label(label);

        // Append image and label to the row
        row.appendChild(images);
        row.appendChild(lab);

        // Set a CSS class for the row
        row.setSclass("sidebar-fn");

        // Create and register event listener for click events
        EventListener<Event> actionListener = new SerializableEventListener<Event>() {
            private static final long serialVersionUID = 1L;

            public void onEvent(Event event) throws Exception {
                // Redirect to the new location URI when the row is clicked
                Executions.getCurrent().sendRedirect(locationUri);
            }
        };

        // Add click event listener to the row
        row.addEventListener(Events.ON_CLICK, actionListener);

        return row;  // Return the constructed row
    }
}
