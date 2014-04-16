/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package methods;

import farmacia.SaidaProduto;
import org.hibernate.HibernateException;
import org.hibernate.event.SaveOrUpdateEvent;
import org.hibernate.event.SaveOrUpdateEventListener;

/**
 *
 * @author UFC
 */
public class Salvar implements SaveOrUpdateEventListener {

    @Override
    public void onSaveOrUpdate(SaveOrUpdateEvent soue) throws HibernateException {
        if (soue.getObject() instanceof SaidaProduto) {
            SaidaProduto sp = (SaidaProduto) soue.getObject();
            if(sp.getQuantidadeSaida() <= (sp.getLote().getQuantidadeTotalEntrada() - sp.getLote().getQuantidadeTotalSaida())){
                sp.addSaida();
            }

        }
    }
}
