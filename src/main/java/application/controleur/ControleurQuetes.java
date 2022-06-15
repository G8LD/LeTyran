package application.controleur;

import application.modele.ModeleQuete;
import application.vue.VueQuetes;

public class ControleurQuetes {

    private ModeleQuete modQuetes;
    private VueQuetes vueQuetes;

    public ControleurQuetes(ModeleQuete mod, VueQuetes vueQuetes) {
        modQuetes = mod;
    }
}
