package com.samp.customermanagement.ui.customer;

import static com.samp.customermanagement.utils.Contants.KEY_HIDE_REMOVE;
import static com.samp.customermanagement.utils.Contants.PREFS_NAME;

import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.samp.customermanagement.core.repository.CustomerRepository;
import com.samp.customermanagement.entity.Customer;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class CustomerViewModel extends AndroidViewModel {
    private final CustomerRepository repository;
    private final MutableLiveData<List<Customer>> allCustomers;
    private final ExecutorService executorService;
    private final Boolean hasRemove;
    public CustomerViewModel(@NonNull Application application) {
        super(application);
        allCustomers = new MutableLiveData<>();
        executorService = Executors.newSingleThreadExecutor();
        repository = new CustomerRepository(application.getApplicationContext());
        SharedPreferences sharedPreferences = application.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        hasRemove = sharedPreferences.getBoolean(KEY_HIDE_REMOVE, false);
        loadAllCustomers(hasRemove);
    }

    public void loadAllCustomers(Boolean hasRemove) {
        executorService.execute(() -> {
            List<Customer> customers = repository.getAllCustomers(hasRemove);
            allCustomers.postValue(customers);
        });
    }

    public LiveData<List<Customer>> getAllCustomers() {
        return allCustomers;
    }

    public void addCustomer(Customer customer) {
            repository.addCustomer(customer);
            loadAllCustomers(hasRemove);
    }

    public void updateCustomer(Customer customer) {
            repository.updateCustomer(customer);
            loadAllCustomers(hasRemove);
    }

    public void logicallyDeleteCustomers(List<Integer> ids) {
            repository.logicallyDeleteCustomers(ids);
            loadAllCustomers(hasRemove);
    }

    public void searchCustomers(String query) {
        executorService.execute(() -> {
            List<Customer> results = repository.searchCustomers(query);
            allCustomers.postValue(results);
        });
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        executorService.shutdown();
        try {
            if (!executorService.awaitTermination(60, TimeUnit.SECONDS)) {
                executorService.shutdownNow();
            }
        } catch (InterruptedException ex) {
            executorService.shutdownNow();
        }
    }
}