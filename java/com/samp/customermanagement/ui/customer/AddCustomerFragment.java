package com.samp.customermanagement.ui.customer;

import static com.samp.customermanagement.utils.Contants.KEY_HIDE_REMOVE;
import static com.samp.customermanagement.utils.Contants.PREFS_NAME;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import com.samp.customermanagement.databinding.FragmentAddcustomerBinding;
import com.samp.customermanagement.entity.Customer;

import java.util.Locale;

public class AddCustomerFragment extends Fragment {
    private FragmentAddcustomerBinding binding;
    private CustomerViewModel customerViewModel;
    private SharedPreferences sharedPreferences;
    private Customer currentCustomer;
    private boolean isEditing = false;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState){
        customerViewModel = new ViewModelProvider(requireActivity()).get(CustomerViewModel.class);
        binding = FragmentAddcustomerBinding.inflate(inflater, container, false);
        sharedPreferences = getActivity().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        boolean hideRemove = sharedPreferences.getBoolean(KEY_HIDE_REMOVE, false);
        View root = binding.getRoot();
        final TextView textView = binding.titleModifiedCustomer;
        if(getArguments() != null){
            int customerId = getArguments().getInt("customer_id", -1);
            customerViewModel.getAllCustomers().observe(getViewLifecycleOwner(), customers -> {
                for (Customer customer : customers) {
                    if (customer.getId() == customerId) {
                        currentCustomer = customer;
                        break;
                    }
                }
                if (currentCustomer != null) {
                    isEditing = true;
                    populateCustomerDetails(currentCustomer);
                    textView.setText("Chỉnh sửa khách hàng");
                } else {
                    Toast.makeText(getActivity(), "Customer not found", Toast.LENGTH_SHORT).show();
                    textView.setText("Tạo khách hàng");
                }
            });
        }
        else {
            textView.setText("Tạo khách hàng");
        }
        Button btnSaveCustomer = binding.btnSaveCustomer;
        btnSaveCustomer.setOnClickListener(v -> saveOrUpdateCustomer());
        return root;
    }

    private void populateCustomerDetails(Customer customer) {
        binding.customerName.setText(customer.getName());
        binding.customerAge.setText(String.valueOf(customer.getAge()));
        binding.customerAddress.setText(customer.getAddress());
        binding.customerSalary.setText(String.format(Locale.getDefault(), "%.2f", customer.getSalary()));
    }

    private void saveOrUpdateCustomer() {
        String name = binding.customerName.getText().toString().trim();
        String ageStr = binding.customerAge.getText().toString().trim();
        String address = binding.customerAddress.getText().toString().trim();
        String salaryStr = binding.customerSalary.getText().toString().trim();

        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(ageStr) ||
                TextUtils.isEmpty(address) || TextUtils.isEmpty(salaryStr)) {
            Toast.makeText(getContext(), "Vui lòng điền đầy đủ các trường", Toast.LENGTH_SHORT).show();
            return;
        }

        int age;
        double salary;
        try {
            age = Integer.parseInt(ageStr);
            salary = Double.parseDouble(salaryStr);
        } catch (NumberFormatException e) {
            Toast.makeText(getContext(), "Định dạng số không hợp lệ", Toast.LENGTH_SHORT).show();
            return;
        }

        if (isEditing) {
            currentCustomer.setName(name);
            currentCustomer.setAge(age);
            currentCustomer.setAddress(address);
            currentCustomer.setSalary(salary);

            try {
                customerViewModel.updateCustomer(currentCustomer);
            } catch (Exception e) {
                Log.e("ErrorUpdate", e.getMessage());
            }
            Toast.makeText(getContext(), "Customer updated successfully", Toast.LENGTH_SHORT).show();
        } else {
            Customer newCustomer = new Customer(name, age, address, salary);
            try {
                customerViewModel.addCustomer(newCustomer);
            } catch (Exception e) {
                Log.e("ErrorAdd", e.getMessage());
            }
            Toast.makeText(getContext(), "Customer added successfully", Toast.LENGTH_SHORT).show();
        }

        NavHostFragment.findNavController(this).popBackStack();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
