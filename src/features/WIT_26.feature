Feature: Scenariusz WIT_26 - [Aplikacja]: Logowanie - prawidłowe dane
  Logowanie na moje konto
  Jako uzytkownik chce móc zalogowac sie na moje konto
  i mieć dostęp do strony głównej

  Scenario Outline: Logowanie sie za pomocą poprawnych danych
    Given  Uzytkownik wchodzi na strone o adresie "<url>"
    When  Uzytkownik wpisuje prawidlowy login "<username>" haslo "<password>" i klika "loguj"
    Then  Uzytkownik jest zalogowany

    Examples:
      | username | password | url                                                 |
      | test     | test     | http://127.0.0.1:8000/account/login/?next=/account/ |
      | test     | test     | http://127.0.0.1:8000/account/login/?next=/account/ |

  Scenario Outline: Log in with invalid data
    Given Uzytkownik wchodzi na strone o adresie "<url>"
    When Uzytkownik wpisuje nieprawidlowy login "<invalidusername>" i nieprawidlowe haslo "<invalidpassword>" i klika "loguj"
    Then Uzytkownik dostaje komunikat o nieprawidlowym zalogowaniu
    Examples:
      | invalidusername | invalidpassword | url                                                 |
      | test122         | test            | http://127.0.0.1:8000/account/login/?next=/account/ |
      | test            | test323         | http://127.0.0.1:8000/account/login/?next=/account/ |

