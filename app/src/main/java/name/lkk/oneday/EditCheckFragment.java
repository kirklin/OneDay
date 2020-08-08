package name.lkk.oneday;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import name.lkk.oneday.data.Check;
import name.lkk.oneday.databinding.FragmentEditCheckBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EditCheckFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EditCheckFragment extends Fragment {
    FragmentEditCheckBinding binding;
    Check thisCheck,changeCheck;

    CheckViewModel checkViewModel;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public EditCheckFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment EditCheckFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static EditCheckFragment newInstance(String param1, String param2) {
        EditCheckFragment fragment = new EditCheckFragment();
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
        binding = FragmentEditCheckBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        checkViewModel = new ViewModelProvider(this).get(CheckViewModel.class);
        thisCheck = (Check) getArguments().getSerializable("check");
        binding.editTextCheckTitle.setText(thisCheck.getTitle());
        binding.editTextCheckContent.setText(thisCheck.getContents());
        binding.buttonEditCheckSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String CheckTitle = binding.editTextCheckTitle.getText().toString();
                String CheckContent = binding.editTextCheckContent.getText().toString();
                thisCheck.setTitle(CheckTitle);
                thisCheck.setContents(CheckContent);
                checkViewModel.updateCheck(thisCheck);
                Toast toast = Toast.makeText(getContext(),"修改成功",Toast.LENGTH_SHORT);
                toast.show();

                NavController navController = Navigation.findNavController(view);
                navController.navigateUp();
            }
        });

    }
}