package name.lkk.oneday;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import name.lkk.oneday.adapter.CheckAdapter;
import name.lkk.oneday.adapter.MainAdapter;
import name.lkk.oneday.data.Check;
import name.lkk.oneday.data.Day;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CheckFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CheckFragment extends Fragment {
    CheckViewModel checkViewModel;
    RecyclerView recyclerView;
    CheckAdapter checkAdapter;
    long dayid=1;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CheckFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CheckFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CheckFragment newInstance(String param1, String param2) {
        CheckFragment fragment = new CheckFragment();
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
        return inflater.inflate(R.layout.fragment_check, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        checkViewModel = new ViewModelProvider(this).get(CheckViewModel.class);
        recyclerView = requireActivity().findViewById(R.id.CheckRecycleView);
        checkAdapter = new CheckAdapter();
        dayid = getArguments().getLong("arg_dayid");
        recyclerView.setLayoutManager(new GridLayoutManager(requireActivity(),2));
        recyclerView.setAdapter(checkAdapter);
        checkViewModel.getAllCheckLive(dayid).observe(requireActivity(), new Observer<List<Check>>() {
            @Override
            public void onChanged(List<Check> checks) {
                int temp = checkAdapter.getItemCount();
                checkAdapter.setAllchecks(checks);
                if (temp != checks.size()) {
                    checkAdapter.notifyDataSetChanged();
                }
            }
        });

    }
}