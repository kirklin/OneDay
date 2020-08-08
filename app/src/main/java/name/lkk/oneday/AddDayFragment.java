package name.lkk.oneday;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import name.lkk.oneday.data.Day;
import name.lkk.oneday.databinding.FragmentAddDayBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddDayFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddDayFragment extends Fragment {
    FragmentAddDayBinding binding;
    MainViewModel mainViewModel;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AddDayFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddDayFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AddDayFragment newInstance(String param1, String param2) {
        AddDayFragment fragment = new AddDayFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
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
        binding = FragmentAddDayBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        FragmentActivity activity = requireActivity();
        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        binding.buttonAddSubmit.setEnabled(false);
        binding.editTextDayTitle.requestFocus();
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(binding.editTextDayTitle,0);
        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String Day =binding.editTextDayTitle.getText().toString().trim();
                binding.buttonAddSubmit.setEnabled(!Day.isEmpty());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        };
        binding.editTextDayTitle.addTextChangedListener(textWatcher);
        binding.buttonAddSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String DayTitle = binding.editTextDayTitle.getText().toString().trim();
                Day day = new Day(DayTitle);
                mainViewModel.insertDay(day);
                NavController navController = Navigation.findNavController(view);
                navController.navigateUp();

            }
        });
    }
}