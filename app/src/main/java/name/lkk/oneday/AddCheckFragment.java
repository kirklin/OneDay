package name.lkk.oneday;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.google.android.material.snackbar.Snackbar;

import name.lkk.oneday.data.Check;
import name.lkk.oneday.databinding.FragmentAddCheckBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddCheckFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddCheckFragment extends Fragment {
    FragmentAddCheckBinding binding;
    CheckViewModel checkViewModel;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AddCheckFragment() {
        // Required empty public constructor
        setHasOptionsMenu(true);
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddCheckFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AddCheckFragment newInstance(String param1, String param2) {
        AddCheckFragment fragment = new AddCheckFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.MenuItemAddCheckDone:
                String CheckTitle = binding.editTextCheckTitle.getText().toString();
                String CheckContent = binding.editTextCheckContent.getText().toString();
                Check check = new Check(getArguments().getLong("arg_dayid"),CheckTitle,CheckContent);
                checkViewModel.insertCheck(check);
                Snackbar.make(binding.getRoot(),"添加成功",Snackbar.LENGTH_SHORT).show();
                InputMethodManager imm = (InputMethodManager) requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(binding.getRoot().getWindowToken(), 0);
                NavController navController = Navigation.findNavController(binding.getRoot());
                navController.navigateUp();
                break;

        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_add_check_right_upper, menu);

    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentAddCheckBinding.inflate(inflater,container,false);
        return binding.getRoot();

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        final FragmentActivity activity = requireActivity();
        checkViewModel = new ViewModelProvider(this).get(CheckViewModel.class);
        binding.editTextCheckContent.requestFocus();
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(binding.editTextCheckContent,0);

    }
}