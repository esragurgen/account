package com.folksdev.account.model

import com.folksdev.account.model.Customer
import com.folksdev.account.model.Transaction
import jakarta.persistence.*
import org.hibernate.annotations.Cascade
import org.hibernate.annotations.GenericGenerator
import java.math.BigDecimal
import java.time.LocalDateTime

/*data class'ın özellikleri:
** içerisinde equals and hashcode ile geliyor.
** toString() ile geliyor.
** immutable (değişmez) bir data oluşturuyor.
** getter setter gibi metodlar ile uğraşmıyoruz.
* */

@Entity
data class Account(

    @Id
    @GeneratedValue(generator = "UUID") //Generate edilecek id'nin 1,2,3,4 gibi tahmin edileblir id'ler değil,
    // oluşturulacak timespend' i alır ve ona göre bir hashcode üretir. Hashcode özelliği data class(kotlin) den geliyor.
    //UUID sadece unique ve tahmin edilemeyen bir id olmasını sağlıyor.
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    val id: String? = "", // gerekmediği sürece 'var' kullanılmamalı. 'val' immutable olmasının sağlıyor.
    val balance: BigDecimal?  = BigDecimal.ZERO,
    val creationDate: LocalDateTime,

    @ManyToOne(fetch =  FetchType.LAZY, cascade = [CascadeType.ALL]) //FetchType.LAZY --> LAZY dediğimizde, uygulamamız her çalıştığında istenilen verileri direkt getirme, sadece çağırıldığında getir demek istiyoruz. EAGER ise her çalıştığında ne kadar veri varsa hepsini getirmeyi sağlıyor.
    @JoinColumn(name = "customer_id", nullable = false)              // CascadeType.ALL --> ALL dediğimizde birbiri ile bağlı tablolardaki verilerden birisi güncellendi(yazma,silme,günc) ise git diğer tabloda da aynı işlemi yapmayı sağlıyor.
    val customer: Customer?,

    @OneToMany(mappedBy = "account", fetch = FetchType.EAGER, cascade = [CascadeType.ALL])
    val transaction: Set<Transaction> = HashSet()



) {
    constructor(customer: Customer, balance: BigDecimal, creationDate: LocalDateTime) : this(
        "",
        customer = customer,
        balance = balance,
        creationDate = creationDate
    )

    //Generate equals and hashcode yaptık. Kendi equals() ve hashCode() metodlarımızı yazdık.
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Account

        if (id != other.id) return false
        if (balance != other.balance) return false
        if (creationDate != other.creationDate) return false
        if (customer != other.customer) return false
        if (transaction != other.transaction) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id?.hashCode() ?: 0
        result = 31 * result + (balance?.hashCode() ?: 0)
        result = 31 * result + creationDate.hashCode()
        result = 31 * result + (customer?.hashCode() ?: 0)
        result = 31 * result + (transaction?.hashCode() ?: 0)
        return result
    }
}