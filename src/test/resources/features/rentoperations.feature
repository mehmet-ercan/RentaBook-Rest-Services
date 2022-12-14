Feature: Kiralama islemleri özelligi
  Scenario: Basarili kitap kiralama senaryosu
    Given Kiralanacak kitap ve musteri bilgileri girilmelidir
    When Kitap kiralama butonuna basildiginda
    Then Kitap ilgili musteriye kiralanmalidir

    Scenario: Kitap kiralama iptal senaryosu
      Given Iptal islemi icin gecerli bir fis numarasi vardir
      When Fis numarasi girilip iptal butonuna basildiginda
      Then Kitap kiralama islemi iptal edilmelidir

  Scenario: Gecerli fis numarasi ile ilgili kiralama bilgilerine erisme senaryosu
    Given Kiralama islemi daha önceden yapilmis olup gecerli bir "fisNumarasi" vardir
    When Fis numarasi girildiginde
    Then Ilgili kiralama bilgilerine erisilir