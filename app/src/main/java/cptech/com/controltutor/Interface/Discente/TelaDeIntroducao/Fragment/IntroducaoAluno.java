package cptech.com.controltutor.Interface.Discente.TelaDeIntroducao.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cptech.com.controltutor.R;


public class IntroducaoAluno extends Fragment {
    public IntroducaoAluno() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_introducao_aluno_fragment_1, container, false);
    }


}
