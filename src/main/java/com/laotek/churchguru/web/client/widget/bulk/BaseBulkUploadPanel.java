package com.laotek.churchguru.web.client.widget.bulk;

import com.google.gwt.event.dom.client.MouseOutEvent;
import com.google.gwt.event.dom.client.MouseOutHandler;
import com.google.gwt.event.dom.client.MouseOverEvent;
import com.google.gwt.event.dom.client.MouseOverHandler;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.SimplePanel;

public abstract class BaseBulkUploadPanel extends Composite {

    protected ScrollPanel topScrollPanel = new ScrollPanel();

    protected ScrollPanel rightScrollPanel = new ScrollPanel();

    protected ScrollPanel leftScrollPanel = new ScrollPanel();

    private Timer goTopScrollTimer = new Timer() {
	@SuppressWarnings("unused")
	public void run() {
	    int currentVerticalScrollPosition = rightScrollPanel
		    .getVerticalScrollPosition();

	    if (currentVerticalScrollPosition > 0) {
		int currentScrollPosition = rightScrollPanel
			.getVerticalScrollPosition() - 15;
		rightScrollPanel
			.setVerticalScrollPosition(currentScrollPosition);
	    } else {
		cancel();
	    }
	}
    };

    private Timer goDownScrollTimer = new Timer() {
	@SuppressWarnings("unused")
	public void run() {
	    int currentScrollPosition = rightScrollPanel
		    .getVerticalScrollPosition() + 15;
	    rightScrollPanel.setVerticalScrollPosition(currentScrollPosition);
	}
    };

    private Timer goLeftScrollTimer = new Timer() {
	@SuppressWarnings("unused")
	public void run() {
	    int currentHorizontalScrollPosition = rightScrollPanel
		    .getHorizontalScrollPosition();

	    if (currentHorizontalScrollPosition > 0) {
		int currentScrollPosition = rightScrollPanel
			.getHorizontalScrollPosition() - 15;
		rightScrollPanel
			.setHorizontalScrollPosition(currentScrollPosition);
		topScrollPanel
			.setHorizontalScrollPosition(currentScrollPosition);
	    } else {
		cancel();
	    }
	}
    };

    private Timer goRightScrollTimer = new Timer() {
	@SuppressWarnings("unused")
	public void run() {
	    int currentScrollPosition = rightScrollPanel
		    .getHorizontalScrollPosition() + 15;
	    rightScrollPanel.setHorizontalScrollPosition(currentScrollPosition);
	    topScrollPanel.setHorizontalScrollPosition(currentScrollPosition);
	}
    };

    protected Image goRight() {
	Image img = new Image("images/app/go_right.png");

	img.addMouseOverHandler(new MouseOverHandler() {
	    @Override
	    public void onMouseOver(MouseOverEvent event) {
		goRightScrollTimer.scheduleRepeating(50);
	    }
	});
	img.addMouseOutHandler(new MouseOutHandler() {
	    @Override
	    public void onMouseOut(MouseOutEvent event) {
		goRightScrollTimer.cancel();
	    }
	});
	return img;
    }

    protected Image goTop() {
	Image img = new Image("images/app/go_top.png");
	img.addMouseOverHandler(new MouseOverHandler() {
	    @Override
	    public void onMouseOver(MouseOverEvent event) {
		goTopScrollTimer.scheduleRepeating(50);
	    }
	});
	img.addMouseOutHandler(new MouseOutHandler() {

	    @Override
	    public void onMouseOut(MouseOutEvent event) {
		goTopScrollTimer.cancel();
	    }
	});
	return img;
    }

    protected Image goDown() {
	Image img = new Image("images/app/go_down.png");
	img.addMouseOverHandler(new MouseOverHandler() {
	    @Override
	    public void onMouseOver(MouseOverEvent event) {
		goDownScrollTimer.scheduleRepeating(50);
	    }
	});
	img.addMouseOutHandler(new MouseOutHandler() {

	    @Override
	    public void onMouseOut(MouseOutEvent event) {
		goDownScrollTimer.cancel();
	    }
	});
	return img;
    }

    protected Image goLeft() {
	Image img = new Image("images/app/go_left.png");
	img.addMouseOverHandler(new MouseOverHandler() {
	    @Override
	    public void onMouseOver(MouseOverEvent event) {
		goLeftScrollTimer.scheduleRepeating(50);
	    }
	});
	img.addMouseOutHandler(new MouseOutHandler() {

	    @Override
	    public void onMouseOut(MouseOutEvent event) {
		goLeftScrollTimer.cancel();
	    }
	});
	return img;
    }

    protected SimplePanel getHeaderPanel() {
	SimplePanel header = new SimplePanel();
	header.add(new HTML("<ul>"

	+ "<li>saved to draft"

	+ "<li>click publish"

	+ "</ul>"));
	return header;
    }
}
