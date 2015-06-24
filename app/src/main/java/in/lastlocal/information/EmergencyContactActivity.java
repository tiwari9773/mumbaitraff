package in.lastlocal.information;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.desarrollodroide.libraryfragmenttransactionextended.FragmentTransactionExtended;

import in.lastlocal.constant.AppConstant;
import in.lastlocal.framework.OnFragmentInteractionListener;
import in.lastlocal.information.fragment.EmergencyContactFragment;
import in.lastlocal.information.fragment.EmergencyContactFragmentMarathi;
import in.lastlocal.mumbaitraffic.R;

public class EmergencyContactActivity extends AppCompatActivity implements OnFragmentInteractionListener {

    private int optionSelected = AppConstant.ANIMATE_X;
    private EmergencyContactFragment mFirstFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faq);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (savedInstanceState == null) {
            mFirstFragment = EmergencyContactFragment.newInstance();
            addFragment(mFirstFragment);
        }
    }

    public void addFragment(Fragment mFirstFragment) {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.add(R.id.fl_fragment_cantainer, mFirstFragment);
        fragmentTransaction.commit();
    }

    /* Overloaded Method*/
    public void replaceFragment(Fragment fragment, Bundle savedInstancState) {
        replaceFragment(fragment, savedInstancState, true);
    }

    //To replace fragments
    public void replaceFragment(Fragment fragment, Bundle savedInstancState, boolean isAdd) {
        FragmentManager fragmentManager = getFragmentManager();
        if (savedInstancState != null)
            fragment.setArguments(savedInstancState);

        if (savedInstancState == null) {
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            if (isAdd)
                transaction.addToBackStack("TAG");

            transaction.replace(R.id.fl_fragment_cantainer, fragment);
            transaction.commit();
        }
    }

    @Override
    public void onFragmentInteraction(View v) {
        if (getFragmentManager().getBackStackEntryCount() == 0) {
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

            FragmentTransactionExtended fragmentTransactionExtended = new FragmentTransactionExtended(this, fragmentTransaction, mFirstFragment, new EmergencyContactFragmentMarathi(), R.id.fl_fragment_cantainer);
            fragmentTransactionExtended.addTransition(optionSelected);
            fragmentTransactionExtended.commit();
        } else {
            getFragmentManager().popBackStack();
        }
    }

    @Override
    public void onBackPressed() {
        int count = getFragmentManager().getBackStackEntryCount();
        if (count == 1) {
            finish();
        }
        super.onBackPressed();
    }

}
