На что советуем обратить внимание.

На данный момент, поля для открытия диалога ввода - TextInputLayout, но по заданию - они не обладают механизмом ввода.  

Создается впечатление, что со списками немного перемудрено.. Сначала скролл идет внутри блоков ячеек (коефициентов и полей инициализации ввода), а потом уже экранный.

Стоит обратить внимание на private lateinit var некоторых переменных, действительно ли это необходимо?

Возможно, стоит рассмотреть другой механизм отлова кликов по полям?
