package com.itcr.clinica.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.*;

import com.itcr.custom.helpers.MapView;

public class MapActivity extends Fragment {


	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        MapView mMapView;


        mMapView = new MapView(this.getActivity());
        return mMapView;
    }

}
