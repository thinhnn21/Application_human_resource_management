package com.samp.customermanagement.ui.customer;

import static com.samp.customermanagement.utils.Contants.CHANNEL_ID;
import static com.samp.customermanagement.utils.Contants.KEY_HIDE_ADDRESS;
import static com.samp.customermanagement.utils.Contants.KEY_HIDE_AGE;
import static com.samp.customermanagement.utils.Contants.KEY_HIDE_REMOVE;
import static com.samp.customermanagement.utils.Contants.KEY_HIDE_SALARY;
import static com.samp.customermanagement.utils.Contants.PREFS_NAME;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.samp.customermanagement.R;
import com.samp.customermanagement.databinding.FragmentDetailcustomerBinding;
import com.samp.customermanagement.entity.Customer;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

public class ViewCustomerFragment extends Fragment {
    private FragmentDetailcustomerBinding binding;
    private CustomerViewModel customerViewModel;
    private SharedPreferences sharedPreferences;
    private Boolean hideRemove;
    private Customer currentCustomer;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState){
        customerViewModel = new ViewModelProvider(requireActivity()).get(CustomerViewModel.class);
        binding = FragmentDetailcustomerBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        TextView textViewDetails = binding.textViewDetails;
        TextView textViewAge = binding.textViewAge;
        TextView textViewAddress = binding.textViewAddress;
        TextView textViewSalary = binding.textViewSalary;
        FloatingActionButton buttonIncreaseSalary = binding.buttonIncreaseSalary;
        Button buttonFirst = binding.buttonFirst;
        Button buttonPrevious = binding.buttonPrevious;
        Button buttonNext = binding.buttonNext;
        Button buttonLast = binding.buttonLast;

        sharedPreferences = getActivity().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        hideRemove = sharedPreferences.getBoolean(KEY_HIDE_REMOVE, false);

        createNotificationChannel();

        customerViewModel.getAllCustomers().observe(getViewLifecycleOwner(), customers -> {
            if (customers != null && !customers.isEmpty()) {
                assert getArguments() != null;
                int customerId = getArguments().getInt("customer_id", 0);
                for (Customer customer : customers) {
                    if (customer.getId() == customerId) {
                        currentCustomer = customer;
                        break;
                    }
                }
                if (currentCustomer != null) {
                    displayCustomer(currentCustomer);
                } else {
                    Toast.makeText(getActivity(), "Customer not found", Toast.LENGTH_SHORT).show();
                    textViewDetails.setText("No customer details available.");
                    textViewAge.setVisibility(View.GONE);
                    textViewAddress.setVisibility(View.GONE);
                    textViewSalary.setVisibility(View.GONE);
                }
            } else {
                Toast.makeText(getActivity(), "No customers found", Toast.LENGTH_SHORT).show();
                textViewDetails.setText("No customer details available.");
                textViewAge.setVisibility(View.GONE);
                textViewAddress.setVisibility(View.GONE);
                textViewSalary.setVisibility(View.GONE);
            }
        });

        buttonFirst.setOnClickListener(v -> showFirst());
        buttonPrevious.setOnClickListener(v -> showPrevious());
        buttonNext.setOnClickListener(v -> showNext());
        buttonLast.setOnClickListener(v -> showLast());

        buttonIncreaseSalary.setOnClickListener(v -> showIncreaseSalaryDialog());
        return root;
    }

    private void showIncreaseSalaryDialog() {
        final EditText input = new EditText(getContext());
        input.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_SIGNED);

        new AlertDialog.Builder(requireContext())
                .setTitle("Increase Salary")
                .setMessage("Enter amount to increase/decrease salary (Current: "+ String.format(Locale.getDefault(), "%.0f", currentCustomer.getSalary()) + "):")
                .setView(input)
                .setPositiveButton("Confirm", (dialog, which) -> {
                    String inputValue = input.getText().toString();
                    if (!inputValue.isEmpty()) {
                        double increaseAmount = Double.parseDouble(inputValue);
                        currentCustomer.setSalary(currentCustomer.getSalary() + increaseAmount);
                        customerViewModel.updateCustomer(currentCustomer);
                        String timeStamp = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault()).format(new Date());
                        String notificationContent = "Already increase salary with amount " + increaseAmount + " at " + timeStamp;

                        showNotification(notificationContent);
                        Toast.makeText(getActivity(), notificationContent, Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("Cancel", (dialog, which) -> dialog.cancel())
                .show();
    }

    @SuppressLint("SetTextI18n")
    private void displayCustomer(Customer customer) {
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
        binding.textViewDetails.setText("ID: " + customer.getId() + "\nName: " + customer.getName());
        binding.textViewAge.setText("Age: " + customer.getAge());
        binding.textViewAddress.setText("Address: " + customer.getAddress());
        binding.textViewSalary.setText("Salary: " + currencyFormat.format(customer.getSalary()));

        applyHideInfoSetting();
    }

    private void showFirst() {
        if (customerViewModel.getAllCustomers().getValue() != null && !customerViewModel.getAllCustomers().getValue().isEmpty()) {
            currentCustomer = customerViewModel.getAllCustomers().getValue().get(0);
            displayCustomer(currentCustomer);
        }
    }

    private void showPrevious() {
        if (currentCustomer == null) return;
        int index = Objects.requireNonNull(customerViewModel.getAllCustomers().getValue()).indexOf(currentCustomer);
        if (index > 0) {
            currentCustomer = customerViewModel.getAllCustomers().getValue().get(index - 1);
            displayCustomer(currentCustomer);
        } else {
            Toast.makeText(getActivity(), "Already at first customer", Toast.LENGTH_SHORT).show();
        }
    }

    private void showNext() {
        if (currentCustomer == null) return;
        int index = Objects.requireNonNull(customerViewModel.getAllCustomers().getValue()).indexOf(currentCustomer);
        if (index < customerViewModel.getAllCustomers().getValue().size() - 1) {
            currentCustomer = customerViewModel.getAllCustomers().getValue().get(index + 1);
            displayCustomer(currentCustomer);
        } else {
            Toast.makeText(getActivity(), "Already at last customer", Toast.LENGTH_SHORT).show();
        }
    }

    private void showLast() {
        if (customerViewModel.getAllCustomers().getValue() != null && !customerViewModel.getAllCustomers().getValue().isEmpty()) {
            int lastIndex = customerViewModel.getAllCustomers().getValue().size() - 1;
            currentCustomer = customerViewModel.getAllCustomers().getValue().get(lastIndex);
            displayCustomer(currentCustomer);
        }
    }

//    private void increaseSalary() {
//        String amountStr = binding.editTextSalaryIncrease.getText().toString().trim();
//        if (TextUtils.isEmpty(amountStr)) {
//            Toast.makeText(getActivity(), "Please enter an amount", Toast.LENGTH_SHORT).show();
//            return;
//        }
//
//        double amount;
//        try {
//            amount = Double.parseDouble(amountStr);
//        } catch (NumberFormatException e) {
//            Toast.makeText(getActivity(), "Invalid amount format", Toast.LENGTH_SHORT).show();
//            return;
//        }
//
//        if (currentCustomer != null) {
//            double newSalary = currentCustomer.getSalary() + amount;
//            currentCustomer.setSalary(newSalary);
//            customerViewModel.updateCustomer(currentCustomer);
//
//            String timeStamp = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault()).format(new Date());
//            String notificationContent = "Already increase salary with amount " + amount + " at " + timeStamp;
//
//            NotificationCompat.Builder builder = new NotificationCompat.Builder(getActivity(), CHANNEL_ID)
//                    .setSmallIcon(R.drawable.baseline_notifications_24)
//                    .setContentTitle("Salary Increased")
//                    .setContentText(notificationContent)
//                    .setPriority(NotificationCompat.PRIORITY_DEFAULT);
//
//            NotificationManager notificationManager = (NotificationManager) getActivity().getSystemService(Context.NOTIFICATION_SERVICE);
//            notificationManager.notify((int) System.currentTimeMillis(), builder.build());
//
//            Toast.makeText(getActivity(), "Salary increased by " + amount, Toast.LENGTH_SHORT).show();
//            binding.editTextSalaryIncrease.setText("");
//            displayCustomer(currentCustomer);
//        }
//    }

    private void applyHideInfoSetting() {
        boolean hideAge = sharedPreferences.getBoolean(KEY_HIDE_AGE, false);
        boolean hideSalary = sharedPreferences.getBoolean(KEY_HIDE_SALARY, false);
        boolean hideAddress = sharedPreferences.getBoolean(KEY_HIDE_ADDRESS, false);

        if (hideAge) {
            binding.textViewAge.setVisibility(View.GONE);
        } else {
            binding.textViewAge.setVisibility(View.VISIBLE);
        }

        if (hideSalary) {
            binding.textViewSalary.setVisibility(View.GONE);
        } else {
            binding.textViewSalary.setVisibility(View.VISIBLE);
        }

        if (hideAddress) {
            binding.textViewAddress.setVisibility(View.GONE);
        } else {
            binding.textViewAddress.setVisibility(View.VISIBLE);
        }
    }

    private void createNotificationChannel() {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            CharSequence name = "Salary Increase Notifications";
            String description = "Notifications for salary increases";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            NotificationManager notificationManager = getActivity().getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    private void showNotification(String notificationContent) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getActivity(), CHANNEL_ID)
                .setSmallIcon(R.drawable.baseline_notifications_24)
                .setContentTitle("Salary Increased")
                .setContentText(notificationContent)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setAutoCancel(true);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(getActivity());
        if (ActivityCompat.checkSelfPermission(this.getActivity(), Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        notificationManager.notify((int) System.currentTimeMillis(), builder.build());
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
