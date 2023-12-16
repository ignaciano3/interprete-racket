(ns tp-105043-garcia-pizales.core-test
  (:require [clojure.test :refer :all]
            [clojure.string :refer :all]
            [tp-105043-garcia-pizales.tp :refer :all]))

(deftest funciones

  (testing "Fnc Append"
    (is (= '(1 2 3 4 5 6 7) (fnc-append '((1 2) (3) (4 5) (6 7)))))
    (is (= (generar-mensaje-error :wrong-type-arg 'append 3) (fnc-append '((1 2) 3 (4 5) (6 7)))))
    (is (= (generar-mensaje-error :wrong-type-arg 'append 'A) (fnc-append '((1 2) A (4 5) (6 7))))))

  (testing "Fnc equal"
    (is (= (generar-mensaje-error :wrong-number-args-prim-proc 'equal?) (fnc-equal? (list))))
    (is (= (generar-mensaje-error :wrong-number-args-prim-proc 'equal?) (fnc-equal? (list 'A))))
    (is (= (symbol "#t") (fnc-equal? (list 'A 'A))))
    (is (= (symbol "#f") (fnc-equal? (list 'A 'a))))
    (is (= (generar-mensaje-error :wrong-number-args-prim-proc 'equal?) (fnc-equal? (list 'A 'A 'A))))
    (is (= (symbol "#t") (fnc-equal? (list (list 1 1) (list 1 1)))))
    (is (= (symbol "#f") (fnc-equal? (list (list 1 1) (list 2 1))))))

  (testing "Fnc sumar"
    (is (= 0 (fnc-sumar (list))))
    (is (= 3 (fnc-sumar (list 3))))
    (is (= 7 (fnc-sumar (list 3 4))))
    (is (= 12 (fnc-sumar (list 3 4 5))))
    (is (= 18 (fnc-sumar (list 3 4 5 6))))
    (is (= (generar-mensaje-error :wrong-type-arg '+ 'A) (fnc-sumar (list 'A 4 5 6))))
    (is (= (generar-mensaje-error :wrong-type-arg '+ 'A) (fnc-sumar (list 3 'A 5 6))))
    (is (= (generar-mensaje-error :wrong-type-arg '+ 'A) (fnc-sumar (list 3 4 'A 6)))))

  (testing "Fnc Restar"
    (is (= (generar-mensaje-error :wrong-number-args 'restar) (fnc-restar (list))))
    (is (= -3 (fnc-restar (list 3))))
    (is (= -1 (fnc-restar (list 3 4))))
    (is (= -6 (fnc-restar (list 3 4 5))))
    (is (= -12 (fnc-restar (list 3 4 5 6))))
    (is (= (generar-mensaje-error :wrong-type-arg '- 'A) (fnc-restar (list 'A 4 5 6))))
    (is (= (generar-mensaje-error :wrong-type-arg '- 'A) (fnc-restar (list 3 'A 5 6))))
    (is (= (generar-mensaje-error :wrong-type-arg '- 'A) (fnc-restar (list 3 4 'A 6)))))

  (testing "Fnc Menor"
    ;(is (= (symbol "#t") (fnc-menor (list)))) No tiene sentido
    ;(is (= (symbol "#t") (fnc-menor (list 1)))) No tiene sentido
    (is (= (symbol "#t") (fnc-menor (list 1 2))))
    (is (= (symbol "#t") (fnc-menor (list 1 2 3))))
    (is (= (symbol "#t") (fnc-menor (list 1 2 3 4))))
    (is (= (symbol "#f") (fnc-menor (list 1 2 2 4))))
    (is (= (symbol "#f") (fnc-menor (list 1 2 1 4))))
    (is (= (generar-mensaje-error :wrong-type-arg '< 'A) (fnc-menor (list 'A 1 2 4))))
    (is (= (generar-mensaje-error :wrong-type-arg '< 'A) (fnc-menor (list 1 'A 2 4))))
    (is (= (generar-mensaje-error :wrong-type-arg '< 'A) (fnc-menor (list 1 2 'A 4)))))

  (testing "Fnc Mayor"
    ;(is (= (symbol "#t") (fnc-mayor (list)))) No tiene sentido
    ;(is (= (symbol "#t") (fnc-mayor (list 1)))) No tiene sentido
    (is (= (symbol "#t") (fnc-mayor (list 2 1))))
    (is (= (symbol "#t") (fnc-mayor (list 3 2 1))))
    (is (= (symbol "#t") (fnc-mayor (list 4 3 2 1))))
    (is (= (symbol "#f") (fnc-mayor (list 4 2 2 1))))
    (is (= (symbol "#f") (fnc-mayor (list 4 2 1 4))))
    (is (= (generar-mensaje-error :wrong-type-arg '> 'A) (fnc-mayor (list 'A 1 2 4))))
    (is (= (generar-mensaje-error :wrong-type-arg '> 'A) (fnc-mayor (list 1 'A 2 4))))
    (is (= (generar-mensaje-error :wrong-type-arg '> 'A) (fnc-mayor (list 1 2 'A 4)))))

  (testing "Fnc Mayor O Igual"
    ;(is (= (symbol "#t") (fnc-mayor-o-igual (list)))) No tiene sentido
    ;(is (= (symbol "#t") (fnc-mayor-o-igual (list 1)))) No tiene sentido
    (is (= (symbol "#t") (fnc-mayor-o-igual (list 2 1))))
    (is (= (symbol "#t") (fnc-mayor-o-igual (list 3 2 1))))
    (is (= (symbol "#t") (fnc-mayor-o-igual (list 4 3 2 1))))
    (is (= (symbol "#t") (fnc-mayor-o-igual (list 4 2 2 1))))
    (is (= (symbol "#f") (fnc-mayor-o-igual (list 4 2 1 4))))
    (is (= (generar-mensaje-error :wrong-type-arg '>= 'A) (fnc-mayor-o-igual (list 'A 1 2 4))))
    (is (= (generar-mensaje-error :wrong-type-arg '>= 'A) (fnc-mayor-o-igual (list 1 'A 2 4))))
    (is (= (generar-mensaje-error :wrong-type-arg '>= 'A) (fnc-mayor-o-igual (list 1 2 'A 4)))))

  (testing "Fnc Read"
    (is (= '(hola mundo) (with-in-str "(hola\nmundo)" (fnc-read))))
    (is (= (generar-mensaje-error :io-ports-not-implemented 'read) (fnc-read '(1))))
    (is (= (generar-mensaje-error :wrong-number-args-prim-proc 'read) (fnc-read '(1 2))))
    (is (= (generar-mensaje-error :wrong-number-args-prim-proc 'read) (fnc-read '(1 2 3)))))
  )

(deftest evaluaciones
  
  (testing "Evaluar Escalar"
    (is (= (list 32 '(x 6 y 11 z "hola")) (evaluar-escalar 32 '(x 6 y 11 z "hola"))))
    (is (= (list "chau" '(x 6 y 11 z "hola")) (evaluar-escalar "chau" '(x 6 y 11 z "hola"))))
    (is (= (list 11 '(x 6 y 11 z "hola")) (evaluar-escalar 'y '(x 6 y 11 z "hola"))))
    (is (= (list "hola" '(x 6 y 11 z "hola")) (evaluar-escalar 'z '(x 6 y 11 z "hola"))))
    (is (= (list (generar-mensaje-error :unbound-variable 'n) '(x 6 y 11 z "hola")) (evaluar-escalar 'n '(x 6 y 11 z "hola")))))

  
  (testing "Evaluar Define"
    (is (= (list (symbol "#<void>") '(x 2)) (evaluar-define '(define x 2) '(x 1))))
    (is (= (list (symbol "#<void>") '(x 1 f (lambda (x) (+ x 1)))) (evaluar-define '(define (f x) (+ x 1)) '(x 1))))
    (is (= (list (generar-mensaje-error :missing-or-extra 'define '(define)) '(x 1)) (evaluar-define '(define) '(x 1))))
    (is (= (list (generar-mensaje-error :missing-or-extra 'define '(define x)) '(x 1)) (evaluar-define '(define x) '(x 1))))
    (is (= (list (generar-mensaje-error :missing-or-extra 'define '(define x 2 3)) '(x 1)) (evaluar-define '(define x 2 3) '(x 1))))
    (is (= (list (generar-mensaje-error :missing-or-extra 'define '(define ())) '(x 1)) (evaluar-define '(define ()) '(x 1))))
    (is (= (list (generar-mensaje-error :bad-variable 'define '(define () 2)) '(x 1)) (evaluar-define '(define () 2) '(x 1))))
    (is (= (list (generar-mensaje-error :bad-variable 'define '(define 2 x)) '(x 1)) (evaluar-define '(define 2 x) '(x 1)))))

  (testing "Evaluar if"
    (is (= (list 7 '(n 7)) (evaluar-if '(if 1 n 8) '(n 7))))
    (is (= (list 8  (list 'n 7 (symbol "#f") (symbol "#f"))) (evaluar-if (list 'if (symbol "#f") 'n 8) (list 'n 7 (symbol "#f") (symbol "#f")))))
    (is (= (list (symbol "#<void>") (list 'n 9 (symbol "#f") (symbol "#f"))) (evaluar-if (list 'if (symbol "#f") 'n '(set! n 9)) (list 'n 7 (symbol "#f") (symbol "#f")))))
    (is (= (list (generar-mensaje-error :missing-or-extra 'if '(if)) '(n 7)) (evaluar-if '(if) '(n 7))))
    (is (= (list (generar-mensaje-error :missing-or-extra 'if '(if 1)) '(n 7)) (evaluar-if '(if 1) '(n 7)))))
  
  (let [amb (list (symbol "#f") (symbol "#f") (symbol "#t") (symbol "#t"))]
    (testing "Evaluar Or"
      (is (= (list (symbol "#f") amb) (evaluar-or (list 'or) amb)))
      (is (= (list (symbol "#t") amb) (evaluar-or (list 'or (symbol "#t")) amb)))
      (is (= (list 7 amb) (evaluar-or (list 'or 7) amb)))
      (is (= (list 5 amb) (evaluar-or (list 'or (symbol "#f") 5) amb)))
      (is (= (list (symbol "#f") amb) (evaluar-or (list 'or (symbol "#f")) amb)))))

  (testing "Evaluar set"
    (is (= (list (symbol "#<void>") '(x 1)) (evaluar-set! '(set! x 1) '(x 0))))
    (is (= (list (generar-mensaje-error :unbound-variable 'x) '()) (evaluar-set! '(set! x 1) '())))
    (is (= (list (generar-mensaje-error :missing-or-extra 'set! '(set! x)) '(x 0)) (evaluar-set! '(set! x) '(x 0))))
    (is (= (list (generar-mensaje-error :missing-or-extra 'set! '(set! x 1 2)) '(x 0)) (evaluar-set! '(set! x 1 2) '(x 0))))
    (is (= (list (generar-mensaje-error :bad-variable 'set! '(set! 1 2)) '(x 0)) (evaluar-set! '(set! 1 2) '(x 0)))))
)


(deftest otros-test

  (testing "Leer Entrada"
    (is (= "(hola mundo)" (with-in-str "(hola\nmundo)" (leer-entrada))))
    (is (= "123" (with-in-str "123" (leer-entrada))))
    (is (= (str (generar-mensaje-error :warning-paren) "(+ 1 3) 3)") (with-in-str "(+ 1 3) 3)" (leer-entrada)))))
  
  (testing "Verificar parentesis"
    (is (= 1 (verificar-parentesis "(hola 'mundo")))
    (is (= -1 (verificar-parentesis "(hola '(mundo)))")))
    (is (= -1 (verificar-parentesis "(hola '(mundo) () 6) 7)")))
    (is (= -1 (verificar-parentesis "(hola '(mundo) () 6) 7) 9)")))
    (is (= 0 (verificar-parentesis "(hola '(mundo) )"))))
  
  (testing "Actualizar ambiente"
    (is (= '(a 1 b 2 c 3 d 4) (actualizar-amb '(a 1 b 2 c 3) 'd 4)))
    (is (= '(a 1 b 4 c 3) (actualizar-amb '(a 1 b 2 c 3) 'b 4)))
    (is (= '(a 1 b 2 c 3) (actualizar-amb '(a 1 b 2 c 3) 'b (list (symbol ";ERROR:") 'mal 'hecho))))
    (is (= '(b 7) (actualizar-amb () 'b 7))))
  
  (testing "Buscar"
    (is (= 3 (buscar 'c '(a 1 b 2 c 3 d 4 e 5))))
    (is (= (generar-mensaje-error :unbound-variable 'f) (buscar 'f '(a 1 b 2 c 3 d 4 e 5)))))
  
  (testing "Error?"
    (is (= true (error? (list (symbol ";ERROR:") 'mal 'hecho))))
    (is (= false (error? (list 'mal 'hecho))))
    (is (= true (error? (list (symbol ";WARNING:") 'mal 'hecho)))))
  
  (testing "Proteger bool en str"
    (is (= "(or %f %t)" (proteger-bool-en-str "(or #f #t)")))
    (is (= "(and (or %f %t) %t)" (proteger-bool-en-str "(and (or #f #t) #t)")))
    (is (= "" (proteger-bool-en-str "")))) 
  
  (testing "Restaurar bool"
    (is (= (list 'and (list 'or (symbol "#F") (symbol "#f") (symbol "#t") (symbol "#T")) (symbol "#T")) (restaurar-bool (read-string (proteger-bool-en-str "(and (or #F #f #t #T) #T)")))))
    (is (= (list 'and (list 'or (symbol "#F") (symbol "#f") (symbol "#t") (symbol "#T")) (symbol "#T")) (restaurar-bool (read-string "(and (or %F %f %t %T) %T)")))))
  
  )