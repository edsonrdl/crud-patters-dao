package com.testeadmissao.testeadmissao.infrastructure.model;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "client")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ClientEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "code_client")
    private Long codeClient;

    @Column(name = "nome", length = 10, nullable = false, unique = false)
    @Size(min = 3, max = 15, message = "O Nome deve ter entre 3 e 10 caracteres")
    @NotBlank(message = "O Nome é obrigatório")
    private String name;

    @Column(name = "CPF", length = 11, nullable = false, unique = false)
    @Size(min = 11, max = 11, message = "O CPF deve conter 11 caracteres")
    @NotBlank(message = "O CPF é obrigatório")
    private String cpf;

    @Column(name = "idade", nullable = false)
    @Min(value = 0, message = "A idade deve ser maior ou igual a zero")
    private int age;
}
