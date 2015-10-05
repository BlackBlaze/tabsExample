package gnf.ada.example.tabs;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.*;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.annotation.WebServlet;


@Title("Tabs")
@Theme("valo")
public class Tabs extends UI {

	private TabSheet ts;
	private final List<Panel> panels = new ArrayList<>();

	@Override
	protected void init(VaadinRequest request) {

		VerticalLayout mainLayout = new VerticalLayout();
		mainLayout.setSpacing(true);
		mainLayout.setMargin(true);

		ts = new TabSheet();
		for (int i = 0; i < 20; i++) {
			Panel p = new Panel("Panel " + (i + 1));
			ts.addTab(p);
			panels.add(p);
		}
		final ComboBox combobox = new ComboBox("Select Tab");
		combobox.setItemCaptionMode(AbstractSelect.ItemCaptionMode.PROPERTY);
		combobox.setItemCaptionPropertyId("caption");
		combobox.setContainerDataSource(new BeanItemContainer(panels));
		combobox.addValueChangeListener(event -> ts.setSelectedTab((Panel) combobox.getValue()));

		mainLayout.addComponent(combobox);
		mainLayout.addComponent(ts);
		setContent(mainLayout);

	}

	/*  Deployed as a Servlet or Portlet.
	 *
	 *  You can specify additional servlet parameters like the URI and UI
	 *  class name and turn on production mode when you have finished developing the application.
	 */
	@WebServlet(urlPatterns = "/*")
	@VaadinServletConfiguration(ui = Tabs.class, productionMode = false)
	public static class MyUIServlet extends VaadinServlet {
	}

}
