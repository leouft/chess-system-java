# Documentação do Sistema de Xadrez (Pacotes e Classes)

Este documento descreve as responsabilidades, atributos e métodos das classes dos pacotes principais do projeto.

Observação: a documentação foi gerada a partir do código-fonte atual no repositório (17/06/2026). Confirme alterações futuras diretamente nos arquivos fonte.

---

## Sumário

- Package: chesslayer
  - ChessMatch
  - ChessPiece
  - ChessPosition
  - ChessException
  - Color
  - (subpackage chesslayer.pieces: Rook, Bishop, Queen, Knight, King, Pawn)

- Package: boardlayer
  - Board
  - Piece
  - Position
  - BoardException

- Package: application
  - UI
  - Program

---

## Package: chesslayer

### Classe: ChessMatch
Responsabilidade:
- Representa uma partida de xadrez: coordena o tabuleiro, peças em jogo, peças capturadas, turno, jogador atual, estados de xeque/xeque-mate, en passant e promoção.

Atributos (visibilidade / tipo / papel):
- private int turn — contador do turno (inicia em 1).
- private Color currentPlayer — jogador atual (WHITE ou BLACK).
- private boolean check — indica se há xeque.
- private boolean checkMate — indica se há xeque-mate.
- private ChessPiece enPassantVulnerable — peça vulnerável a en passant (ou null).
- private ChessPiece promoted — peça que foi promovida (ou null até promoção ocorrer).
- private Board board — referência ao tabuleiro.
- private List<Piece> piecesOnBoard — peças atualmente em jogo.
- private List<Piece> capturedPieces — peças capturadas.

Métodos públicos principais:
- ChessMatch() — construtor; inicializa board 8x8, turn, currentPlayer e chama initialSetup().
- List<Piece> getPiecesOnBoard() — retorna as peças em jogo.
- int getTurn(), Color getCurrentPlayer(), boolean getCheck(), boolean getCheckMate(), ChessPiece getEnPassantVulnerable(), ChessPiece getPromoted() — getters.
- ChessPiece[][] getPieces() — retorna matriz de ChessPiece do tabuleiro.
- boolean[][] possibleMoves(ChessPosition sourcePosition) — valida origem e retorna matriz de movimentos possíveis.
- ChessPiece performChessMove(ChessPosition sourcePosition, ChessPosition targetPosition) — executa movimento de xadrez (inclui capturas, roque, en passant e promoção), valida se movimento deixa jogador em xeque (desfaz e lança ChessException), atualiza estado do jogo e retorna peça capturada (ou null).
- ChessPiece replacePromotedPiece(String type) — substitui peça promovida por nova peça do tipo informado ("B","N","Q","R"); lança IllegalStateException se não houver promoted.

Métodos privados auxiliares:
- next() — incrementa turno e troca jogador.
- newPiece(String type, Color color) — factory de peças para promoção.
- Piece makeMove(Position source, Position target) — realiza movimentação física no tabuleiro (remove, coloca, trata roque e en passant) e retorna a peça capturada.
- void unmakeMove(Position source, Position target, Piece capturedPiece) — desfaz makeMove (reverte movimentos, roque e en passant). (Nota: bug de en passant foi corrigido.)
- newPieceOnBoard(char column, int row, ChessPiece piece) — coloca peça no tabuleiro usando ChessPosition e adiciona à lista piecesOnBoard.
- Color opponent(Color color) — retorna cor oposta.
- ChessPiece locateKing(Color color) — localiza rei da cor dada (lança IllegalStateException se não encontrar).
- boolean isCheck(Color color) — checa se o rei de determinada cor está em xeque.
- boolean isCheckMate(Color color) — determina se é xeque-mate.
- void initialSetup() — posiciona peças iniciais no tabuleiro.
- void validateSourcePosition(Position position), void validateTargetPosition(Position source, Position target) — validações que lançam ChessException com mensagens informativas.

Observações:
- Mensagens de exceção contêm instruções para UI ([PRESS ENTER TO CONTINUE]) — mantido conforme preferência.

---

### Classe: ChessPiece (abstract)
Responsabilidade:
- Representa a peça de xadrez com atributos e comportamentos comuns: cor, contagem de movimentos, utilitários de captura.

Atributos:
- private Color color
- private int moveCount

Métodos:
- ChessPiece(Board board, Color color) — construtor.
- Color getColor(), int getMoveCount()
- protected boolean isThereOpponentPiece(Position position) — true se posição contém peça adversária.
- protected void increaseMoveCount(), protected void decreaseMoveCount()
- public ChessPosition getChessPosition() — converte Position interna para ChessPosition.

---

### Classe: ChessPosition
Responsabilidade:
- Representa posições no formato de xadrez (ex.: a1..h8) e converte para Position (índices 0-based) usado pelo Board.

Atributos:
- private char column
- private int row

Métodos:
- ChessPosition(char column, int row) — valida range (a..h, 1..8); lança ChessException se inválido.
- char getColumn(), int getRow()
- protected Position toPosition() — converte a notação de xadrez para Position (8-row, column - 'a').
- protected static ChessPosition fromPosition(Position position) — converte Position para ChessPosition.
- @Override String toString() — retorna notação legível (ex.: "e4").

---

### Classe: ChessException
Responsabilidade:
- Exceção de domínio para regras de xadrez.

Métodos:
- ChessException(String message) — construtor delegando para RuntimeException.

---

### Enum: Color
Valores: BLACK, WHITE

---

### Package: chesslayer.pieces (resumo das peças)

Para cada peça: construtor, implementação de possibleMoves() e toString() correspondendo à notação (R, B, Q, N, K, P conforme implementado no código).

- Rook: movimentos horizontais/verticais; toString() -> "R".
- Bishop: movimentos diagonais; toString() -> "B".
- Queen: combina rook + bishop; toString() geralmente "Q".
- Knight: movimentos em L (8 saltos); toString() -> "N".
- King: movimentos de uma casa + roque (usa ChessMatch para verificar condições); toString() geralmente "K".
- Pawn: movimentos frontais, dois passos no primeiro movimento, capturas diagonais, en passant e promoção; usa ChessMatch para en passant/promo.

Observações:
- King e Pawn recebem ChessMatch no construtor para aplicar regras que dependem do estado global (roque, en passant, promoção).

---

## Package: boardlayer

### Classe: Board
Responsabilidade:
- Gerencia matriz de Piece, valida posições, realiza place/remove e consultas.

Atributos:
- private int rows
- private int columns
- private Piece[][] pieces

Métodos:
- Board(int rows, int columns) — valida dimensões e inicializa matriz; lança BoardException se inválido.
- int getRows(), int getColumns()
- Piece piece(int row, int column)
- Piece piece(Position position)
- void placePiece(Piece piece, Position position)
- Piece removePiece(Position position)
- boolean positionExists(Position position)
- boolean thereIsAPiece(Position position)

Observações:
- placePiece atualiza piece.position internamente.

---

### Classe: Piece (abstract)
Responsabilidade:
- Abstração base para peças: guarda referência ao Board e posição; define contrato possibleMoves().

Atributos:
- protected Position position
- private Board board

Métodos:
- Piece(Board board)
- Board getBoard()
- abstract boolean[][] possibleMoves()
- boolean possibleMove(Position position)
- boolean isThereAnyPossibleMove() — percorre possibleMoves() buscando true.

Observação de correção aplicada (no código-fonte): a iteração interna foi ajustada para usar aux[i].length no segundo índice para evitar problemas em tabuleiros não-quadrados.

---

### Classe: Position
Responsabilidade:
- Representa coordenadas internas (row, column).

Atributos:
- private int row
- private int column

Métodos:
- Position(int row, int column)
- getters/setters e setValues(int row, int column)
- @Override String toString()

Observação de correção aplicada (no código-fonte): toString() foi ajustado para formato apropriado (por exemplo: "%d, %d").

---

### Classe: BoardException
- Extende RuntimeException; usada para erros do tabuleiro.

---

## Package: application

### Classe: UI
Responsabilidade:
- Utilitários de I/O no console: leitura de posições, impressão do tabuleiro, peças capturadas, cores ANSI e limpeza de tela.

Principais métodos:
- ChessPosition readChessPosition(Scanner sc) — lê entrada do usuário (ex.: "e2") e retorna ChessPosition; lança InputMismatchException em caso de formato inválido.
- printPiece(ChessPiece piece, boolean background) — imprime peça com cores e background quando necessário.
- printBoard(ChessPiece[][] pieces) e printBoard(ChessPiece[][] pieces, boolean[][] possibleMoves) — imprimem tabuleiro; assumem notação 8x8 e colunas a..h.
- clearScreen() — limpa console via ANSI.
- printMatch(ChessMatch match, List<ChessPiece> capturedPieces) — imprime estado da partida.

Observações:
- Mensagens e prompts estão formatados para console com ANSI; mantidos conforme preferência do autor.

---

### Classe: Program
Responsabilidade:
- Classe com método main que inicia e controla loop de jogo por console, integrando UI e ChessMatch.

Fluxo principal:
- Inicializa Scanner, ChessMatch e lista de capturadas.
- Loop de jogo: imprime estado, lê source, mostra possíveis movimentos, lê target, executa performChessMove(), registra capturas, trata promoção, trata exceções e continua até checkmate.

---

## Onde revisar o código
- Source: https://github.com/leouft/chess-system-java/tree/main/src
- Package chesslayer: https://github.com/leouft/chess-system-java/tree/main/src/chesslayer
- Package chesslayer.pieces: https://github.com/leouft/chess-system-java/tree/main/src/chesslayer/pieces
- Package boardlayer: https://github.com/leouft/chess-system-java/tree/main/src/boardlayer
- Package application: https://github.com/leouft/chess-system-java/tree/main/src/application


---
