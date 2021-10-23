package com.voleer.test.ui.pages;

import com.voleer.test.ui.core.BasePage;
import com.voleer.test.ui.core.Navigatable;
import com.voleer.test.ui.pages.components.ContactSalesForm;

public class MigrationDiscoverySolutionPage extends BasePage implements Navigatable {

    @Override
    public String getPageUrl() {
        return "https://voleer.com/contact-sales/?SQF_PLAN=discovery-365-solution";
    }

    public ContactSalesForm contactSalesForm() {
        return new ContactSalesForm();
    }
}
