package ee.ttu.olivereivak.webbasedapps.repair.entity.repairshop;

import static javax.persistence.CascadeType.MERGE;
import static javax.persistence.CascadeType.PERSIST;
import static javax.persistence.FetchType.EAGER;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

import ee.ttu.olivereivak.webbasedapps.repair.entity.subject.Employee;

@Entity
@Getter
@Setter
@Table(name = "service_order")
@SequenceGenerator(name = "service_order_seq", sequenceName = "service_order_id", allocationSize = 1)
public class ServiceOrder {

    @Id
    @GeneratedValue(generator = "service_order_seq")
    @Column(name = "service_order")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "so_status_type_fk")
    private ServiceOrderStatusType serviceOrderStatusType;

    @ManyToOne(cascade = { MERGE, PERSIST })
    @JoinColumn(name = "service_request_fk")
    private ServiceRequest serviceRequest;

    @ManyToOne
    @JoinColumn(name = "created_by")
    private Employee creator;

    @ManyToOne
    @JoinColumn(name = "updated_by")
    private Employee updater;

    private Instant created;

    private Instant updated;

    @Column(name = "status_changed")
    private Instant statusChanged;

    @ManyToOne
    @JoinColumn(name = "status_changed_by")
    private Employee statusChanger;

    @Column(name = "price_total")
    private BigDecimal totalPrice;

    @Column(columnDefinition = "text")
    private String note;

    @OneToMany(fetch = EAGER, cascade = { MERGE, PERSIST })
    @JoinColumn(name = "service_order_fk")
    private List<ServiceDevice> serviceDevices;

    @OneToMany(fetch = EAGER, cascade = { MERGE, PERSIST })
    @JoinColumn(name = "service_order_fk")
    private List<ServicePart> serviceParts;

    @OneToMany(fetch = EAGER, cascade = { MERGE, PERSIST })
    @JoinColumn(name = "service_order_fk")
    private List<ServiceAction> serviceActions;

}