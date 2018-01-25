package com.msc.gwt.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class Gwt implements EntryPoint {

	private SimplePanel card;
	private DockPanel dockPanel;
	
	public void onModuleLoad() {
		// root
		RootLayoutPanel root = RootLayoutPanel.get();
//		RootPanel root = RootPanel.get();
//		root.getElement().setId("1");
		root.setSize("100%", "100%");
//		root.setSize("500px", "500px");
//		RootPanel.get().add(arg0);
//		root.se

		// card
		card = createCardPanel();

		// bank selector
		VerticalPanel bankSelector = createBankSelector();

		// dock panel
		dockPanel = createDockPanel();

		card.setWidget(bankSelector);
		
//		VerticalPanel v = new VerticalPanel();
//		v.setWidth("100%");
//		v.setHeight("100%");
//		v.add(card);
//		root.add(v);
		root.add(card);
//		root.add(dockPanel);
	}

	private ListBox createActionPanel() {
		ListBox actions = new ListBox();
		actions.addItem("残高照会");
		actions.addItem("入出金明細照会");
		actions.addItem("振込入金明細照会");
		actions.addItem("振込申請");
		actions.setVisibleItemCount(5);
		return actions;
	}

	private ScrollPanel createDisplay() {
		ScrollPanel scrollPanel = new ScrollPanel();
		scrollPanel.add(new Label("display"));
		return scrollPanel;
	}

	private DockPanel createDockPanel() {
		DockPanel dockPanel = new DockPanel();
		dockPanel.setSize("100%", "100%");
		dockPanel.setStyleName("cw-DockPanel");
		dockPanel.setHorizontalAlignment(DockPanel.ALIGN_CENTER);
		// north
		dockPanel.add(createTitle(), DockPanel.NORTH);

		// west
		dockPanel.add(createActionPanel(), DockPanel.WEST);

		// center
		dockPanel.add(createDisplay(), DockPanel.EAST);

		// south
		dockPanel.add(createFooter(), DockPanel.SOUTH);

		return dockPanel;
	}

	private Widget createFooter() {
		Label footer = new Label("Footer");
//		footer.set
		return footer;
	}

	private Label createTitle() {
		Label title = new Label("Title");
		return title;
	}

	private SimplePanel createCardPanel() {
		SimplePanel cardPanel = new SimplePanel();
//		cardPanel.setSize("300px", "500px");
//		cardPanel.se
		return cardPanel;
	}

	private VerticalPanel createBankSelector() {
		VerticalPanel verticalPanel = new VerticalPanel();
		Button mufg = new Button("三菱東京UFJ銀行");
		Button mizuho = new Button("みずほ銀行");
		Button smbc = new Button("三井住友銀行");
		Button netbk = new Button("住信SBIネット銀行");
		verticalPanel.add(mufg);
		verticalPanel.add(mizuho);
		verticalPanel.add(smbc);
		verticalPanel.add(netbk);
		mufg.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent arg0) {
				//create mufg dock
				card.setWidget(dockPanel);
			}
		});
		mizuho.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent arg0) {
				// switch
			}
		});
		smbc.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent arg0) {
				// switch
			}
		});
		netbk.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent arg0) {
				// switch
			}
		});
		return verticalPanel;
	}
}
