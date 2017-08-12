package mum.edu.Domain;

import javax.persistence.Embeddable;

/**
 * Created by Hatake on 8/11/2017.
 */
@Embeddable
public enum OrderStatus {
    NEW,CANCELLED,CHECKEDOUT,SHIPPED,DELIVERED
}
