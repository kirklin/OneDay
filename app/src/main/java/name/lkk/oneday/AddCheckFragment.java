package name.lkk.oneday;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

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
        FragmentActivity activity = requireActivity();
        checkViewModel = new ViewModelProvider(this).get(CheckViewModel.class);
        binding.buttonCheckSubmit.setEnabled(false);
        binding.editTextCheckContent.requestFocus();
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(binding.editTextCheckContent,0);
        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String Day =binding.editTextCheckContent.getText().toString().trim();
                binding.buttonCheckSubmit.setEnabled(!Day.isEmpty());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        };
        binding.editTextCheckContent.addTextChangedListener(textWatcher);
        binding.buttonCheckSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String CheckTitle = binding.editTextCheckTitle.getText().toString();
                String CheckContent = binding.editTextCheckContent.getText().toString();
                Check check = new Check(getArguments().getLong("arg_dayid"),CheckTitle,CheckContent);
                checkViewModel.insertCheck(check);
                Toast toast = Toast.makeText(getContext(),"添加成功",Toast.LENGTH_SHORT);
                toast.show();
                NavController navController = Navigation.findNavController(view);
                navController.navigateUp();

            }
        });
    }
}