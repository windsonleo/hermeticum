package com.teccsoluction.hermeticum.view;

import java.util.ResourceBundle;

public enum FxmlView {

    USER {
        @Override
		public String getTitle() {
            return getStringFromResourceBundle("user.title");
        }

        @Override
		public String getFxmlFile() {
            return "/fxml/Usuario.fxml";
        }
    }, 
   
    
    LOGIN {
        @Override
		public String getTitle() {
            return getStringFromResourceBundle("login.title");
        }

        @Override
		public String getFxmlFile() {
            return "/fxml/Login.fxml";
        }
    },
    
    MAIN {
        @Override
		public String getTitle() {
            return getStringFromResourceBundle("main.title");
        }

        @Override
		public String getFxmlFile() {
            return "/fxml/main.fxml";
        }
    },
	
    MOVIMENTACAOEMPRESA {
        @Override
		public String getTitle() {
            return getStringFromResourceBundle("mov.empresa");
        }

        @Override
		public String getFxmlFile() {
            return "/fxml/movimentacaoempresa.fxml";
        }
    },
    
    MOVIMENTACAOFUNCIONARIO {
        @Override
		public String getTitle() {
            return getStringFromResourceBundle("mov.funcionario");
        }

        @Override
		public String getFxmlFile() {
            return "/fxml/movimentacaofuncionario.fxml";
        }
    },
    
    MOVIMENTACAOCLIENTE {
        @Override
		public String getTitle() {
            return getStringFromResourceBundle("mov.cliente");
        }

        @Override
		public String getFxmlFile() {
            return "/fxml/movimentacaocliente.fxml";
        }
    },
    
    MOVIMENTACAOFORNECEDOR {
        @Override
		public String getTitle() {
            return getStringFromResourceBundle("mov.fornecedor");
        }

        @Override
		public String getFxmlFile() {
            return "/fxml/movimentacaofornecedor.fxml";
        }
    },
    
    MOVIMENTACAOCATEGORIA {
        @Override
		public String getTitle() {
            return getStringFromResourceBundle("mov.categoria");
        }

        @Override
		public String getFxmlFile() {
            return "/fxml/movimentacaocategoria.fxml";
        }
    },
    
    MOVIMENTACAOPRODUTO {
        @Override
		public String getTitle() {
            return getStringFromResourceBundle("mov.produto");
        }

        @Override
		public String getFxmlFile() {
            return "/fxml/movimentacaoproduto.fxml";
        }
    },
    
    MOVIMENTACAOCOMPRAS {
        @Override
		public String getTitle() {
            return getStringFromResourceBundle("mov.compras");
        }

        @Override
		public String getFxmlFile() {
            return "/fxml/movimentacaocompras.fxml";
        }
    },
    
    MOVIMENTACAOVENDAS {
        @Override
		public String getTitle() {
            return getStringFromResourceBundle("mov.vendas");
        }

        @Override
		public String getFxmlFile() {
            return "/fxml/movimentacaovendas.fxml";
        }
    },
    
    MOVIMENTACAOFORMAPAGAMENTO {
        @Override
		public String getTitle() {
            return getStringFromResourceBundle("mov.formapagamento");
        }

        @Override
		public String getFxmlFile() {
            return "/fxml/movimentacaoformapagamento.fxml";
        }
    },
    
    MOVIMENTACAOCAIXA{
        @Override
		public String getTitle() {
            return getStringFromResourceBundle("mov.caixa");
        }

        @Override
		public String getFxmlFile() {
            return "/fxml/movimentacaocaixa.fxml";
        }
    },
    
    MOVIMENTACAOUSUARIO{
        @Override
		public String getTitle() {
            return getStringFromResourceBundle("mov.usuario");
        }

        @Override
		public String getFxmlFile() {
            return "/fxml/movimentacaousuario.fxml";
        }
    },
    
    MOVIMENTACAOCONTASPAGAR{
        @Override
		public String getTitle() {
            return getStringFromResourceBundle("mov.contaspagar");
        }

        @Override
		public String getFxmlFile() {
            return "/fxml/movimentacaocontaspagar.fxml";
        }
    },
    
    MOVIMENTACAOCONTASRECEBER{
        @Override
		public String getTitle() {
            return getStringFromResourceBundle("mov.contasreceber");
        }

        @Override
		public String getFxmlFile() {
            return "/fxml/movimentacaocontasreceber.fxml";
        }
    },
    
    MOVIMENTACAOPAGAMENTO{
        @Override
		public String getTitle() {
            return getStringFromResourceBundle("mov.pagamento");
        }

        @Override
		public String getFxmlFile() {
            return "/fxml/movimentacaopagamento.fxml";
        }
    },
    
    MOVIMENTACAOPEDIDOVENDA{
        @Override
		public String getTitle() {
            return getStringFromResourceBundle("mov.pedidovenda");
        }

        @Override
		public String getFxmlFile() {
            return "/fxml/movimentacaopedidovenda.fxml";
        }
    },
    
    MOVIMENTACAORECEBIMENTO{
        @Override
		public String getTitle() {
            return getStringFromResourceBundle("mov.recebimento");
        }

        @Override
		public String getFxmlFile() {
            return "/fxml/movimentacaorecebimento.fxml";
        }
    },
    
    MOVIMENTACAOPEDIDOCOMPRA{
        @Override
		public String getTitle() {
            return getStringFromResourceBundle("mov.pedidocompra");
        }

        @Override
		public String getFxmlFile() {
            return "/fxml/movimentacaopedidocompra.fxml";
        }
    },
    
    MOVIMENTACAOESTOQUE{
        @Override
		public String getTitle() {
            return getStringFromResourceBundle("mov.estoque");
        }

        @Override
		public String getFxmlFile() {
            return "/fxml/movimentacaoestoque.fxml";
        }
    },
    
    
    
    PDV{
        @Override
		public String getTitle() {
            return getStringFromResourceBundle("mov.pdv");
        }

        @Override
		public String getFxmlFile() {
            return "/fxml/pdv.fxml";
        }
    },
    
    
    MOVIMENTACAOFINANCEIRO{
        @Override
		public String getTitle() {
            return getStringFromResourceBundle("mov.pdv");
        }

        @Override
		public String getFxmlFile() {
            return "/fxml/movimentacaofinanceiro.fxml";
        }
    },
    
    
    MOVIMENTACAOCONFIGURACAO {
        @Override
		public String getTitle() {
            return getStringFromResourceBundle("mov.configuracao");
        }

        @Override
		public String getFxmlFile() {
            return "/fxml/movimentacaoconfiguracao.fxml";
        }
    };
    
    public abstract String getTitle();
    public abstract String getFxmlFile();
    
    String getStringFromResourceBundle(String key){
        return ResourceBundle.getBundle("Bundle").getString(key);
    }

}
