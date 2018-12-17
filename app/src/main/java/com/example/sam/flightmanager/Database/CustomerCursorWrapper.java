// Sam Westigard
// CST 338
// FLIGHTMANAGER Project
// wrapper for customer cursor
package com.example.sam.flightmanager.Database;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.example.sam.flightmanager.Objects.CustomerItem;

public class CustomerCursorWrapper extends CursorWrapper {
    public CustomerCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public CustomerItem getCustomerItem() {
//        int id = getInt(getColumnIndex(SystemSchema.CustomersTable.Cols.ID));
        String username = getString(getColumnIndex(SystemSchema.CustomersTable.Cols.USERNAME));
        String password = getString(getColumnIndex(SystemSchema.CustomersTable.Cols.PASSWORD));

        CustomerItem item = new CustomerItem();

//        item.setID(id);
        item.setPassword(password);
        item.setUsername(username);

        return item;
    }

}
