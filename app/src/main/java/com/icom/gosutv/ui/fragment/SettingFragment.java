package com.icom.gosutv.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ToggleButton;
import butterknife.InjectView;
import com.icom.gosutv.R;
import com.icom.gosutv.base.BaseFragment;
import com.icom.gosutv.ui.customview.SettingsToggle;
import com.icom.gosutv.ui.listener.ToggleButtonListener;

/**
 * Created by Trung on 8/28/2015.
 */
public class SettingFragment extends BaseFragment
{
    @InjectView(R.id.setting_screen_toggleButtonSetup)
    SettingsToggle toggleButton;
    @InjectView(R.id.setting_screen_toggleButtonDota)
    SettingsToggle toggleButtonDota;
    @InjectView(R.id.setting_screen_toggleButtonLol)
    SettingsToggle toggleButtonLol;

    @InjectView(R.id.setting_fragment_rlFilterDota)
    RelativeLayout rlFilterDota;

    @InjectView(R.id.setting_fragment_rlFilterLol)
    RelativeLayout rlFilterLol;

    @Override
    public int getLayout()
    {
        return R.layout.setting_fragment;
    }

    @Override
    public void setupView()
    {
        SharedPreferences sp = getActivity().getSharedPreferences(getActivity().getString(R.string.preferences_key), Context.MODE_PRIVATE);
        boolean isSetup = sp.getBoolean("setupFilter", false);
        if (isSetup)
        {
            disable(rlFilterDota,true);
            disable(rlFilterLol,true);
            rlFilterDota.setAlpha(1);
            rlFilterLol.setAlpha(1);
        }
        else
        {
            disable(rlFilterDota,false);
            disable(rlFilterLol,false);
            rlFilterDota.setAlpha(0.5f);
            rlFilterLol.setAlpha(0.5f);
        }
        toggleButton.setToggleButtonListener(new ToggleButtonListener()
        {
            @Override
            public void onClick()
            {
                SharedPreferences sp = getActivity().getSharedPreferences(getActivity().getString(R.string.preferences_key), Context.MODE_PRIVATE);
                boolean isSetup = sp.getBoolean("setupFilter", false);
                if (isSetup)
                {
                    disable(rlFilterDota,true);
                    disable(rlFilterLol,true);
                    rlFilterDota.setAlpha(1);
                    rlFilterLol.setAlpha(1);
                }
                else
                {
                    disable(rlFilterDota,false);
                    disable(rlFilterLol,false);
                    rlFilterDota.setAlpha(0.5f);
                    rlFilterLol.setAlpha(0.5f);
                }
            }
        });
        toggleButtonDota.setToggleButtonListener(new ToggleButtonListener()
        {
            @Override
            public void onClick()
            {

            }
        });
        toggleButtonLol.setToggleButtonListener(new ToggleButtonListener()
        {
            @Override
            public void onClick()
            {

            }
        });
    }

    private void disable(ViewGroup layout, boolean enable)
    {
        for (int i = 0; i < layout.getChildCount(); i++)
        {
            View child = layout.getChildAt(i);
            child.setEnabled(enable);
            if (child instanceof ViewGroup)
            {
                ViewGroup group = (ViewGroup) child;
                for (int j = 0; j < group.getChildCount(); j++)
                {
                    group.getChildAt(j).setEnabled(enable);
                }
            }

        }
    }
}
