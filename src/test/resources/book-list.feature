Feature: Kitaplar listelenebilir
  Scenario: /books adresine GET isteği yapılır
    Given API servisi başlatıldı
    When /books adresine istek yaptığımda
    Then kitap listesini görmeliyim
