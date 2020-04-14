package com.zwh.mvp.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zwh.mvp.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ItemFragment extends Fragment {


    TextView textView;

    private String title;

    public static ItemFragment getInstance(String label) {
        ItemFragment sf = new ItemFragment();
        Bundle args = new Bundle();
        args.putString("title", label);
        sf.setArguments(args);
        return sf;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_item, container, false);
        textView = view.findViewById(R.id.textView);
        textView.setText(title);
        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
          title = bundle.getString("title");
        }
    }

}
