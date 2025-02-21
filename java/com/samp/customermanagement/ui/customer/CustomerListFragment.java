package com.samp.customermanagement.ui.customer;

import static com.samp.customermanagement.utils.Contants.KEY_HIDE_ADDRESS;
import static com.samp.customermanagement.utils.Contants.KEY_HIDE_AGE;
import static com.samp.customermanagement.utils.Contants.KEY_HIDE_REMOVE;
import static com.samp.customermanagement.utils.Contants.PREFS_NAME;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;
import com.samp.customermanagement.R;
import com.samp.customermanagement.databinding.FragmentCustomersBinding;
import com.samp.customermanagement.databinding.ItemTransformBinding;
import com.samp.customermanagement.entity.Customer;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class CustomerListFragment extends Fragment {
    private FragmentCustomersBinding binding;
    private SharedPreferences sharedPreferences;
    private CustomerViewModel customerViewModel;
    private Context context;
    private final List<Integer> selectedCustomers = new ArrayList<>();
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState){
        customerViewModel = new ViewModelProvider(requireActivity()).get(CustomerViewModel.class);
        binding = FragmentCustomersBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        RecyclerView dataTableCustomers = binding.datatableCustomers;
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        sharedPreferences = getActivity().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        context = getContext();
        dataTableCustomers.setLayoutManager(layoutManager);
        TransformAdapter adapter = new TransformAdapter();
        dataTableCustomers.setAdapter(adapter);
        boolean hideRemove = sharedPreferences.getBoolean(KEY_HIDE_REMOVE, false);
        customerViewModel.getAllCustomers().observe(getViewLifecycleOwner(), customers -> {
            if (customers != null) {
                adapter.submitList(customers);
            }
        });
        binding.fabDeleteCustomer.setVisibility(View.GONE);
        binding.fabDeleteCustomer.setOnClickListener(view -> {
            showDeleteDialog();
        });
        return root;
    }

    private void showDeleteDialog() {
        new AlertDialog.Builder(requireContext())
                .setTitle("Xác nhân xóa")
                .setPositiveButton("Xác nhận", (dialog, which) -> {
                    if (!selectedCustomers.isEmpty()) {
                        customerViewModel.logicallyDeleteCustomers(selectedCustomers);
                        selectedCustomers.clear();
                        updateFloatingButtonVisibility();
                        ((TransformAdapter) binding.datatableCustomers.getAdapter()).clearSelections();
                        Toast.makeText(getActivity(), "Bạn đã xóa dữ liệu thành công", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("Hủy bỏ", (dialog, which) -> dialog.cancel())
                .show();
    }
    private void updateFloatingButtonVisibility() {
        if (!selectedCustomers.isEmpty()) {
            binding.fabDeleteCustomer.setVisibility(View.VISIBLE);
        } else {
            binding.fabDeleteCustomer.setVisibility(View.GONE);
        }
    }

    private class TransformAdapter extends ListAdapter<Customer, TransformViewHolder> {

        private final List<Integer> drawables = Arrays.asList(
                R.drawable.avatar_1,
                R.drawable.avatar_2,
                R.drawable.avatar_3,
                R.drawable.avatar_4,
                R.drawable.avatar_5,
                R.drawable.avatar_6,
                R.drawable.avatar_7,
                R.drawable.avatar_8,
                R.drawable.avatar_9,
                R.drawable.avatar_10,
                R.drawable.avatar_11,
                R.drawable.avatar_12,
                R.drawable.avatar_13,
                R.drawable.avatar_14,
                R.drawable.avatar_15,
                R.drawable.avatar_16);

        protected TransformAdapter() {
            super(new DiffUtil.ItemCallback<Customer>() {
                @Override
                public boolean areItemsTheSame(@NonNull Customer oldItem, @NonNull Customer newItem) {
                    return oldItem.equals(newItem);
                }

                @Override
                public boolean areContentsTheSame(@NonNull Customer oldItem, @NonNull Customer newItem) {
                    return oldItem.equals(newItem);
                }
            });
        }

        @NonNull
        @Override
        public TransformViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            ItemTransformBinding binding = ItemTransformBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
            return new TransformViewHolder(binding);
        }

        @SuppressLint("SetTextI18n")
        @Override
        public void onBindViewHolder(@NonNull TransformViewHolder holder, int position) {
            boolean hideAddress = sharedPreferences.getBoolean(KEY_HIDE_ADDRESS, false);
            boolean hideAge= sharedPreferences.getBoolean(KEY_HIDE_AGE, false);
            holder.checkboxDelete.setOnCheckedChangeListener(null);
            Customer customer = getItem(position);
            holder.checkboxDelete.setChecked(selectedCustomers.contains(customer.getId()));
            holder.textView.setText(customer.getName());
            boolean hideRemove= sharedPreferences.getBoolean(KEY_HIDE_REMOVE, false);
            if(hideRemove && customer.getDeleteDate() != null)
            {
                holder.textDelete.setText("Ngày xóa: " + String.valueOf(customer.getDeleteDate()));
                holder.textDelete.setVisibility(View.VISIBLE);
            }else {
                holder.textDelete.setVisibility(View.GONE);
            }
            if (hideAge) {
                holder.textAge.setVisibility(View.GONE);
            } else {
                holder.textAge.setText("Tuổi: " + String.valueOf(customer.getAge()));
                holder.textAge.setVisibility(View.VISIBLE);
            }
            if (hideAddress) {
                holder.textAddress.setVisibility(View.GONE);
            } else {
                holder.textAddress.setText("Địa chỉ: " + customer.getAddress());
                holder.textAddress.setVisibility(View.VISIBLE);
            }
            int drawableRes = drawables.get(position % drawables.size());
            holder.imageView.setImageDrawable(
                    ResourcesCompat.getDrawable(holder.imageView.getResources(),
                            drawableRes,
                            null));
            holder.mainLayout.setOnClickListener(view -> {
                Bundle bundle = new Bundle();
                bundle.putInt("customer_id", customer.getId());
                NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_content_main);
                navController.navigate(R.id.btnViewCustomer, bundle);
            });

            holder.btnEdit.setOnClickListener(view -> {
                Bundle bundle = new Bundle();
                bundle.putInt("customer_id", customer.getId());
                NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_content_main);
                navController.navigate(R.id.btnAddCustomer, bundle);
            });

            holder.checkboxDelete.setOnCheckedChangeListener((buttonView, isChecked) -> {
                if (isChecked) {
                    selectedCustomers.add(customer.getId());
                } else {
                    selectedCustomers.remove(Integer.valueOf(customer.getId()));
                }
                updateFloatingButtonVisibility();
            });
        }
        public void clearSelections() {
            notifyDataSetChanged();
        }

    }

    private class TransformViewHolder extends RecyclerView.ViewHolder {

        private final ImageView imageView;
        private final TextView textView;
        private final TextView textAge;
        private final LinearLayout mainLayout;
        private final TextView textAddress;
        private final Button btnEdit;
        private final CheckBox checkboxDelete;
        private final TextView textDelete;
        public TransformViewHolder(ItemTransformBinding binding) {
            super(binding.getRoot());
            imageView = binding.imageViewItemTransform;
            textView = binding.textViewItemName;
            textAge = binding.textViewItemAge;
            mainLayout = binding.mainItemLayout;
            textAddress = binding.textViewItemDescription;
            btnEdit = binding.btnEditCustomer;
            checkboxDelete = binding.checkboxSelect;
            textDelete = binding.textViewItemDelete;
            Animation translate_anim = AnimationUtils.loadAnimation(context, R.anim.translate_anim);
            mainLayout.setAnimation(translate_anim);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
