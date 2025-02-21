package com.samp.customermanagement.core.repository;

import android.content.Context;

import com.samp.customermanagement.entity.Customer;
import com.samp.customermanagement.utils.DatabaseHelper;

import java.util.List;

public class CustomerRepository {
    private final DatabaseHelper dbHelper;

    public CustomerRepository(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    public void addCustomer(Customer customer) {
        dbHelper.addCustomer(customer);
    }

    public List<Customer> getAllCustomers(Boolean hasRemove) {
        return dbHelper.getAllCustomers(hasRemove);
    }

    public Customer getCustomerById(int id) {
        return dbHelper.getCustomerById(id);
    }

    public void updateCustomer(Customer customer) {
        dbHelper.updateCustomer(customer);
    }

    public void increaseSalary(double amount) {
        dbHelper.increaseSalary(amount);
    }

    public void logicallyDeleteCustomers(List<Integer> ids) {
        dbHelper.logicallyDeleteCustomers(ids);
    }

    public List<Customer> searchCustomers(String query) {
        return dbHelper.searchCustomers(query);
    }
}
