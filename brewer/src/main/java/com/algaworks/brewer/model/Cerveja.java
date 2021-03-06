package com.algaworks.brewer.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.util.StringUtils;

import com.algaworks.brewer.repository.listener.CervejaEntityListener;
import com.algaworks.brewer.validation.SKU;

@EntityListeners(CervejaEntityListener.class)
@Entity
@Table(name="cerveja")
public class Cerveja implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long codigo;
	
	@SKU
	@NotBlank
	private String sku;
	
	@NotBlank(message="Nome é obrigatório")
	private String nome;
	
	@NotBlank
	@Size(max=50, message="O tamanho da descrição deve estar entre 1 e 50")
	private String descricao;
	
	@NotNull(message="Valor deve ser informado")
	@DecimalMax(value="9999999999.99", message="O valor deve ser menor que 9.999.999.999,99")
	@DecimalMin(value="0.01", message="O valor não pode ser menor que 0,01")
	private BigDecimal valor;
	
	@NotNull(message="Teor Alcóolico deve ser informado")
	@Column(name="teor_alcoolico")
	private BigDecimal teorAlcoolico;
	
	@NotNull(message="Comissão deve ser informado")
	@DecimalMax(value="100", message="Comissão não pode ser mais que 100")
	private BigDecimal comissao;
	
	@Column(name="quantidade_estoque")
	@Max(value=9999, message="Quantidade em estoque deve ser menor que 9999")
	@NotNull(message="Quantidade em estoque deve ser informado")
	private Integer quantidadeEstoque;
	
	@Enumerated(EnumType.STRING)
	@NotNull(message="A origem deve ser informada")
	private Origem origem;
	
	@Enumerated(EnumType.STRING)
	@NotNull(message="O sabor deve ser informado")
	private Sabor sabor;
	
	@ManyToOne
	@JoinColumn(name="codigo_estilo")
	@NotNull(message="O estilo deve ser informado")
	private Estilo estilo;
	
	private String foto;
	
	@Column(name = "content_type")
	private String contentType;
	
	@Transient
	private String urlFoto;
	
	@Transient
	private String urlThumbnailFoto;
	
	@PrePersist @PreUpdate
	private void prePersistUpdate() {
		sku = sku.toUpperCase();
	}
	
	public String getSku() {
		return sku;
	}
	public void setSku(String sku) {
		this.sku = sku;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Long getCodigo() {
		return codigo;
	}
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	public BigDecimal getValor() {
		return valor;
	}
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	public BigDecimal getTeorAlcoolico() {
		return teorAlcoolico;
	}
	public void setTeorAlcoolico(BigDecimal teorAlcoolico) {
		this.teorAlcoolico = teorAlcoolico;
	}
	public BigDecimal getComissao() {
		return comissao;
	}
	public void setComissao(BigDecimal comissao) {
		this.comissao = comissao;
	}
	public Integer getQuantidadeEstoque() {
		return quantidadeEstoque;
	}
	public void setQuantidadeEstoque(Integer quantidadeEstoque) {
		this.quantidadeEstoque = quantidadeEstoque;
	}
	public Origem getOrigem() {
		return origem;
	}
	public void setOrigem(Origem origem) {
		this.origem = origem;
	}
	public Sabor getSabor() {
		return sabor;
	}
	public void setSabor(Sabor sabor) {
		this.sabor = sabor;
	}
	public Estilo getEstilo() {
		return estilo;
	}
	public void setEstilo(Estilo estilo) {
		this.estilo = estilo;
	}
	
	public String getFoto() {
		return foto;
	}
	
	public String getFotoOuMock() {
		return StringUtils.isEmpty(foto) ? "cerveja-mock.png" : foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	
	public boolean temFoto() {
	    return !StringUtils.isEmpty(this.foto);
	}
	
	public boolean isNova() {
	   return codigo == null;
	}
	
    public String getUrlFoto() {
        return urlFoto;
    }

    public void setUrlFoto(String urlFoto) {
        this.urlFoto = urlFoto;
    }
    
    public String getUrlThumbnailFoto() {
        return urlThumbnailFoto;
    }
    
    public void setUrlThumbnailFoto(String urlThumbnailFoto) {
        this.urlThumbnailFoto = urlThumbnailFoto;
    }
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cerveja other = (Cerveja) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

}
