Feature: Satış işlemleri özelliği
  Scenario: Başarılı kitap satışı senaryosu
    Given Satın alınacak kitap ve müşteri bilgileri girilmelidir
    When Kitap satışı butonuna basıldığında
    Then Kitap ilgili müşteriye satılmış olmalıdır

  Scenario: Kitap satışı iptal senaryosu
    Given İptal işemi için geçerli bir fiş numarası vardır
    When Fiş numarası girilip iptal butonuna basıldığında
    Then Satınalma işlemi iptal edilmelidir

  Scenario: Geçerli fiş numarası ile ilgili satış bilgilerine erişme senaryosu
    Given Satınalma işlemi daha önceden yapılmış olup geçerli bir fiş numarası vardır
    When Fiş numarası girildiğince
    Then İlgili satınalma bilgilerine erişilir
