import java.io.*;

class Nodo {
    int info;
    Nodo esq, dir;

}

public class ArvoreBin {
    private Nodo raiz;

    public ArvoreBin() {
        raiz = null;
    }

    public Nodo alocarNodo(int valor) {
        Nodo novoNodo = new Nodo();
        novoNodo.info = valor;
        novoNodo.esq = novoNodo.dir = null;
        return novoNodo;
    }

    public void inserir(int valor) {
        raiz = inserir(valor, raiz);
    }

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

    public void preOrdem() {
        preOrdem(raiz);
    }

    private void preOrdem(Nodo raiz) {
        if (raiz != null) {
            System.out.print(raiz.info + " ");
            preOrdem(raiz.esq);
            preOrdem(raiz.dir);
        }
    }

    public void central() {
        central(raiz);
    }

    private void central(Nodo raiz) {
        if (raiz != null) {
            central(raiz.esq);
            System.out.print(raiz.info + " ");
            central(raiz.dir);
        }
    }

    public void posOrdem() {
        posOrdem(raiz);
    }

    private void posOrdem(Nodo raiz) {
        if (raiz != null) {
            posOrdem(raiz.esq);
            posOrdem(raiz.dir);
            System.out.print(raiz.info + " ");
        }

    }

    public void remover(int valor) {
        raiz = remover(raiz, valor);
    }

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

    public boolean buscar(int valor) {
        return buscar(valor, raiz) != null;
    }

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

    private int buscarMin(Nodo raiz) {
        int minv = raiz.info;
        while (raiz.esq != null) {
            minv = raiz.esq.info;
            raiz = raiz.esq;
        }
        return minv;
    }

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