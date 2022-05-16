package modulos.produto;

import dataFactory.ProdutoDataFactory;
import dataFactory.UsuarioDataFactory;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

@DisplayName("Testes de API do modulo de produto")
public class ProdutoTest {
    private String token;

    @BeforeEach
    public void beforeEach() {
        // Setup
        baseURI = "http://165.227.93.41";
        // port = 8080
        basePath = "/lojinha";

        // Token
        this.token = given()
                .contentType(ContentType.JSON)
                .body(UsuarioDataFactory.criarUsuarioAdministrador())
            .when()
                .post("/v2/login")
            .then()
                .extract()
                .path("data.token");
    }

    @Test
    @DisplayName("Validar que o valor zero no cadastro do produto nao e permitido")
    public void testValidarValorZeroProduto() {
        // Inserir produto com valor 0.00 e validar que a mensagem de erro foi exibida e o status code retornado foi 422
        given()
            .contentType(ContentType.JSON)
            .header("token", this.token)
            .body(ProdutoDataFactory.criarProdutoComumComOValorIgualA(0.00))
        .when()
            .post("v2/produtos")
        .then()
            .assertThat()
                .body("error", equalTo("O valor do produto deve estar entre R$ 0,01 e R$ 7.000,00"))
                .statusCode(422);
    }

    @Test
    @DisplayName("Validar que valor maior que sete mil no cadastro do produto nao e permitido")
    public void testValidarValorMaiorSeteMilProduto() {
        // Inserir produto com valor 7000.01 e validar que a mensagem de erro foi exibida e o status code retornado foi 422
        given()
            .contentType(ContentType.JSON)
            .header("token", this.token)
            .body(ProdutoDataFactory.criarProdutoComumComOValorIgualA(7000.01))
        .when()
            .post("v2/produtos")
        .then()
            .assertThat()
                .body("error", equalTo("O valor do produto deve estar entre R$ 0,01 e R$ 7.000,00"))
                .statusCode(422);
    }
}
