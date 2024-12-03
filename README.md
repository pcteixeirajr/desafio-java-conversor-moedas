
# **Mercado de Pulgas dos Mil Saberes** – Sistema de Conversão de Moedas

**Bem-vindo ao projeto de conversão de moedas desenvolvido para o Reino de SRM!**  
Aqui, embarcamos em uma jornada mágica para resolver um desafio intrigante: a integração econômica entre duas culturas
distintas – os comerciantes de Wefin e os anões das montanhas, cada qual com suas moedas e regras de comércio.

Prepare-se para explorar cada detalhe desse sistema que não só traduz valores monetários, mas também conecta mundos e
fortalece laços comerciais!

---

## 📜 **Contexto do Projeto**

Na cidade de **Wefin**, coração do Reino de SRM, o comércio prospera. De tempos em tempos, o mercado se transforma em
um verdadeiro espetáculo chamado **Mercado de Pulgas dos Mil Saberes**, onde mercadores negociam desde peles finas até
hidromel raro.

Mas uma situação incomum surge: **os anãos comerciantes** trouxeram consigo o **Tibar**, uma moeda exótica que não
circula normalmente em Wefin, onde a economia se baseia no **Ouro Real**.

Nosso desafio?  
**Criar um sistema capaz de converter moedas de forma confiável, eficiente e customizável, garantindo que o comércio
prospere sem obstáculos.**

---

## ⚙️ **Tecnologias Utilizadas**

- **Back-End**: Java 17, Springboot
- **Banco de Dados**: PostgreSQL
- **Documentação da API**: Swagger (OpenAPI)
- **Gerenciamento de Versões**: Git
- **Validação e Testes**: Mockito para testes unitários, Postman para testes de integração

---

## 🛠️ **Funcionalidades Principais**

### 1. **Conversão de Moedas em Tempo Real**
- Converte valores entre **Ouro Real** e **Tibar**.
- Ajusta a taxa de câmbio diariamente, conforme a demanda e os acordos comerciais.

### 2. **Conversão Personalizada por Produto**
- Cada produto pode ter uma **taxa de conversão específica** baseada em sua natureza, raridade e origem.  
  Exemplo:
    - Hidromel pode ser convertido com uma taxa diferente de Madeira.

### 3. **API RESTful**
- Desenvolvida com o conceito de **API First**, permite:
    - **Consultar taxas de câmbio** atuais.
    - **Realizar conversões** entre moedas para um produto específico.
    - **Consultar o histórico** de transações.

### 4. **Resiliência e Consistência**
- Todas as transações são realizadas em **blocos transacionais** que garantem que nenhuma operação seja perdida,
  mesmo em caso de falhas.

### 5. **Consultas Avançadas**
- Permite consultas detalhadas por:
    - **Data** da transação
    - **Reino de origem** do produto
    - **Tipo de moeda**
    - **Produto negociado**

---

### **Acesso ao Swagger**:
```
http://localhost:8080/swagger-ui.html
```

---

## 🔍 **Endpoints da API**

### **Base URL**:
```
https://api.mercado-de-pulgas-srm.com/v1
```

### 1. **Consultar Taxa de Câmbio Atual**
**GET** `/taxa-de-cambio`  
Retorna a taxa atual entre **Ouro Real** e **Tibar**.

#### Exemplo de Response:
```json
{
  "status": "success",
  "data": {
    "moeda_origem": "Ouro Real",
    "moeda_destino": "Tibar",
    "taxa_atual": 2.5,
    "data_ultima_atualizacao": "2024-11-28T10:00:00Z"
  }
}
```

---

### 2. **Conversão de Moeda para um Produto**
**POST** `/conversao`

#### Request Body:
```json
{
  "moeda_origem": "Ouro Real",
  "moeda_destino": "Tibar",
  "valor": 100,
  "produto": "Hidromel"
}
```

#### Exemplo de Response:
```json
{
  "status": "success",
  "data": {
    "moeda_origem": "Ouro Real",
    "moeda_destino": "Tibar",
    "produto": "Hidromel",
    "valor_original": 100,
    "valor_convertido": 250,
    "taxa_utilizada": 2.5,
    "data_transacao": "2024-11-28T10:05:00Z"
  }
}
```

---

### 3. **Consultar Histórico de Transações**
**GET** `/historico`

#### Query Params:
- `moeda` – Filtrar por moeda (ex: "Ouro Real")
- `data_inicial` e `data_final` – Filtrar por intervalo de datas
- `produto` – Filtrar por produto (ex: "Madeira")

#### Exemplo de Response:
```json
{
  "status": "success",
  "data": [
    {
      "id_transacao": 1,
      "produto": "Peles",
      "moeda_origem": "Ouro Real",
      "moeda_destino": "Tibar",
      "valor_original": 100,
      "valor_convertido": 250,
      "data_transacao": "2024-11-27T14:00:00Z"
    }
  ]
}
```

---

## 📊 **Modelagem do Banco de Dados**

### **Entidades Principais**:

#### **Moeda**
| Campo      | Tipo        | Descrição                |
|------------|-------------|--------------------------|
| tipo_moeda | SERIAL (PK) | Identificador único      |
| nome_moeda | VARCHAR(50) | Nome da moeda            |

#### **Produto**
| Campo          | Tipo         | Descrição                |
|----------------|--------------|--------------------------|
| id_produto     | SERIAL (PK)  | Identificador único      |
| nome_produto   | VARCHAR(100) | Nome do produto          |
| id_reino_origem| INT (FK)     | Reino de origem          |

#### **TaxaDeCambio**
| Campo            | Tipo         | Descrição                |
|------------------|--------------|--------------------------|
| id_taxa_cambio   | SERIAL (PK)  | Identificador único      |
| id_moeda_origem  | INT (FK)     | Moeda de origem          |
| id_moeda_destino | INT (FK)     | Moeda de destino         |
| taxa_cambio      | FLOAT        | Taxa de conversão        |
| data_taxa_cambio | TIMESTAMP    | Data da taxa             |

#### **Transacao**
| Campo           | Tipo         | Descrição                |
|-----------------|--------------|--------------------------|
| id_transacao    | SERIAL (PK)  | Identificador único      |
| id_produto      | INT (FK)     | Produto negociado        |
| valor_final     | FLOAT        | Valor final da transação |
| data_transacao  | TIMESTAMP    | Data da transação        |

---

## 📜 **Script SQL (Criação das Tabelas)**

```sql
CREATE TABLE Moeda (
    id_moeda SERIAL PRIMARY KEY,
    nome_moeda VARCHAR(50) NOT NULL
);

CREATE TABLE Produto (
    id_produto SERIAL PRIMARY KEY,
    nome_produto VARCHAR(100) NOT NULL,
    id_reino_origem INT REFERENCES Reino(id_reino)
);

CREATE TABLE TaxaDeCambio (
    id_taxa_cambio SERIAL PRIMARY KEY,
    id_moeda_origem INT REFERENCES Moeda(id_moeda),
    id_moeda_destino INT REFERENCES Moeda(id_moeda),
    taxa-cambio FLOAT NOT NULL,
    data_taxa_cambio TIMESTAMP NOT NULL
);

CREATE TABLE Transacao (
    id_transacao SERIAL PRIMARY KEY,
    id_produto INT REFERENCES Produto(id_produto),
    valor_final FLOAT NOT NULL,
    data_transacao TIMESTAMP NOT NULL,
    id_moeda_origem INT REFERENCES Moeda(id_moeda),
    id_moeda_destino INT REFERENCES Moeda(id_moeda)
);
```

---

## ✨ **Conclusão**

Este sistema foi projetado para ser robusto, flexível e preparado para lidar com as complexidades de um mercado onde
culturas e economias convergem. Esperamos que ele não só resolva o desafio técnico proposto, mas também inspire novas
soluções criativas para o comércio digital em um mundo cada vez mais interconectado.

**Contribuições, feedbacks e melhorias são sempre bem-vindos!** 🌟

---  
**Desenvolvido com dedicação por Paulo Júnior**  
**Contato**: pcteixeirajr@gmail.com | [GitHub](https://github.com/seuperfil)
