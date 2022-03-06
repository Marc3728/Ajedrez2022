package es.ieslavereda.model;

public interface IDeletePieceManager {
    void add(Piece piece);
    Piece getFirst();
    void remove(Piece piece);
    int count(ChessType chessType);
}
