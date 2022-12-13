Feature: Kiralama işlemleri özelliği
  Scenario: Başarılı kitap kiralama senaryosu
    Given Kiralanacak kitap ve müşteri bilgileri girilmelidir
    When Kitap kiralama butonuna basıldığında
    Then Kitap ilgili müşteriye kiralanmalıdır

    Scenario: Kitap kiralama iptal senaryosu
      Given İptal işemi için geçerli bir fiş numarası vardır
      When Fiş numarası girilip iptal butonuna basıldığında
      Then Kitap kiralama işlemi iptal edilmelidir

      Scenario: Geçerli fiş numarası ile ilgili kiralama bilgilerine erişme senaryosu
        Given Kiralama işlemi daha önceden yapılmış olup geçerli bir fiş numarası vardır
        When Fiş numarası girildiğince
        Then İlgili kiralama bilgilerine erişilir
