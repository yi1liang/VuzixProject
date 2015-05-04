package com.tdoc.vuzixproject;

import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class MainFragment extends Fragment implements View.OnClickListener {
    private Button startScanButton, startPackingListButton;
    private View rootView;
    private String[] wordList = {"back", "scan", "packing list", "perpetual inventory system"};


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_main, container, false);

        startScanButton = (Button) rootView.findViewById(R.id.startScanButton);
        startPackingListButton = (Button) rootView.findViewById(R.id.startPackingListButton);
        startScanButton.setOnClickListener(this);
        startPackingListButton.setOnClickListener(this);

        // Inflate the layout for this fragment
        return rootView;
    }


    @Override // Currently just for testing physical buttons and gestures on the M100, and for use on phones
    public void onClick(View v) {
        if (v == startScanButton) {
            Log.i("Button pressed: ", "startScanButton");

            Fragment fragment = new SingleScanFragment();
            this.getFragmentManager().beginTransaction()
                    .setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out, android.R.animator.fade_in, android.R.animator.fade_out)
                    .replace(R.id.fragmentcontainer, fragment, "FRAG_SINGLE_SCAN")
                    .addToBackStack(null)
                    .commit();

        } if (v == startPackingListButton) {
            Log.i("Button pressed: ", "startPackingListButton");

            Fragment fragment = new PackingListFragment();
            this.getFragmentManager().beginTransaction()
                    .setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out, android.R.animator.fade_in, android.R.animator.fade_out)
                    .replace(R.id.fragmentcontainer, fragment, "FRAG_PACK")
                    .addToBackStack(null)
                    .commit();
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        if (MainActivity.isThereVoice){
            MainActivity.voiceCtrl.setCallingFragment(this);
            MainActivity.voiceCtrl.setWordlist(wordList);
        }
    }

}
