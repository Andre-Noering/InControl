package trabalhofinal.model.entity;

@Data
@Entity
@Table(name="pedido_compra")
public class PedidoCompraEntity {
	@Column(name="id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="data")
	private Date data;
}
