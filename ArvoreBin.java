import java.io.*;

//Classe criada para gerar objetos "Nodo", que serão a base de toda a nossa árvore binária, tendo cada Nodo então um valor e até dois nodos filhos.
class Nodo {
    int info;
    Nodo esq, dir;

}

//Classe de Controle da Árvore Binária, que já vem por padrão com o nodo raiz, como padrão para iniciar uma BST.
public class ArvoreBin {
    private Nodo raiz;

    //Através do construtor da classe, definimos como vazio o nodo raiz.
    public ArvoreBin() {
        raiz = null;
    }

    //Método realizado para realizar a criação de um novo Nodo à BST.
    public Nodo alocarNodo(int valor) {
        Nodo novoNodo = new Nodo();
        novoNodo.info = valor;
        novoNodo.esq = novoNodo.dir = null;
        return novoNodo;
    }

    //Método que realiza a recursividade para inserir um novo Nodo na árvore Binária, buscando então o espaço ideal para alocação do mesmo.
    public void inserir(int valor) {
        raiz = inserir(valor, raiz);
    }

    //Método que realiza a busca do local ideal para inserir um novo Nodo na árvore Binária, onde é feita então a verificação dos valores dos Nodos em questão para saber se o ideal é pô-lo a esquerda ou direita.
    private Nodo inserir(int valor, Nodo raiz) {
        if (raiz == null) {
            raiz = alocarNodo(valor);
            return raiz;
        }

        else {
            if (valor < raiz.info) {
                raiz.esq = inserir(valor, raiz.esq);
            } else {
                raiz.dir = inserir(valor, raiz.dir);
            }
        }
        return raiz;
    }

    //Método recursivo para realizar a navegação pela BST em pré-ordem;
    public void preOrdem() {
        preOrdem(raiz);
    }

    //Método que realiza a navegação pela BST em pré-ordem, verificando primeiramente a raiz, depois todos os valores à esquerda, e após, todos os valores à direita;
    private void preOrdem(Nodo raiz) {
        if (raiz != null) {
            System.out.print(raiz.info + " ");
            preOrdem(raiz.esq);
            preOrdem(raiz.dir);
        }
    }

    //Método recursivo para realizar a navegação pela BST em ordem;
    public void central() {
        central(raiz);
    }

    //Método que realiza a navegação pela BST em ordem, verificando primeiro todos os valores à esquerda, depois a raiz e, após, todos os valores à direita;
    private void central(Nodo raiz) {
        if (raiz != null) {
            central(raiz.esq);
            System.out.print(raiz.info + " ");
            central(raiz.dir);
        }
    }

    //Método recursivo para realizar a navegação pela BST em pós-ordem;
    public void posOrdem() {
        posOrdem(raiz);
    }

    //Método que realiza a navegação pela BST em ordem, verificando primeiro todos os valores à esquerda, depois todos os valores à direita e, após, o valor da raiz;
    private void posOrdem(Nodo raiz) {
        if (raiz != null) {
            posOrdem(raiz.esq);
            posOrdem(raiz.dir);
            System.out.print(raiz.info + " ");
        }

    }

    //Método que realiza a recursividade para remover um Nodo na árvore Binária.
    public void remover(int valor) {
        raiz = remover(raiz, valor);
    }

    //Método que realiza a busca de um determinado valor para remover da BST e mantê-la organizada ainda dentro dos padrões.
    Nodo remover(Nodo raiz, int valor) {
        if (raiz == null) {
            return raiz;
        }

        if (valor < raiz.info)
            raiz.esq = remover(raiz.esq, valor);
        else if (valor > raiz.info)
            raiz.dir = remover(raiz.dir, valor);
        else {
            if (raiz.esq == null)
                return raiz.dir;
            else if (raiz.dir == null)
                return raiz.esq;

            raiz.info = buscarMin(raiz.dir);
            raiz.dir = remover(raiz.dir, raiz.info);
        }
        return raiz;
    }

    //Método que realiza a busca por um determinado valor na BST através da recursividade.
    public boolean buscar(int valor) {
        return buscar(valor, raiz) != null;
    }

    //Método que realiza a busca por um determinado valor na BST.
    private Nodo buscar(int valor, Nodo raiz) {
        if (raiz == null || raiz.info == valor) {
            return raiz;
        }

        else {
            if (valor < raiz.info) {
                return buscar(valor, raiz.esq);
            } else {
                return buscar(valor, raiz.dir);
            }
        }
    }
    //Método que auxilia no processo de remoção de um nodo verificando o valor mínimo para manter a árvore binária organizada.
    private int buscarMin(Nodo raiz) {
        int minv = raiz.info;
        while (raiz.esq != null) {
            minv = raiz.esq.info;
            raiz = raiz.esq;
        }
        return minv;
    }

    //Método que realiza a criação de um arquivo ".dot" conforme o exigido pelo Software GraphViz para visualizar graficamente a árvore gerada.
    public void gerarArqDot(String filename) {
        try (BufferedWriter out = new BufferedWriter(new FileWriter(filename))) {
            out.write("digraph ArvoreBin {\n");
            out.write("node [shape=circle, style=filled, color=black, fillcolor=\"#9370DB\"];\n");
            out.write("edge [color=black];\n");
            escreverPreOrdemDot(raiz, out);
            out.write("}");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Método que escreve a navegação da árvore em pré-ordem para auxiliar na criação do documento ".dot".
    private void escreverPreOrdemDot(Nodo raiz, BufferedWriter out) throws IOException {
        if (raiz != null) {
            out.write("  " + raiz.info + ";\n");
            if (raiz.esq != null) {
                out.write("  " + raiz.info + " -> " + raiz.esq.info + ";\n");
            }
            if (raiz.dir != null) {
                out.write("  " + raiz.info + " -> " + raiz.dir.info + ";\n");
            }
            escreverPreOrdemDot(raiz.esq, out);
            escreverPreOrdemDot(raiz.dir, out);
        }
    }

    //Classe main para executar o código, testando diferentes funcionalidades dá árvore.
    public static void main(String[] args) {
        ArvoreBin arvore = new ArvoreBin();
        arvore.inserir(568);
        arvore.inserir(455);
        arvore.inserir(698);
        arvore.inserir(369);
        arvore.inserir(40);
        arvore.inserir(100);
        arvore.inserir(756);
        arvore.inserir(861);
        arvore.inserir(900);
        arvore.inserir(68);
        arvore.inserir(51);
        arvore.inserir(7);
        arvore.inserir(9);
        arvore.inserir(17);
        arvore.inserir(186);
        arvore.inserir(405);
        arvore.inserir(814);
        arvore.inserir(61);
        arvore.inserir(231);
        arvore.inserir(506);

        System.out.println("Caminhamento pré-ordem:");
        arvore.preOrdem();

        System.out.println("\nCaminhamento em ordem:");
        arvore.central();

        System.out.println("\nCaminhamento pós-ordem:");
        arvore.posOrdem();

        // Salvar no arquivo dot pra visualização
        arvore.gerarArqDot("arvoreBin3.dot");

    }
}