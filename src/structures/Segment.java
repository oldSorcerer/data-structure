package structures;

/*Этот класс не нужен, так как он должен быть привать приватом в реализациях.
Дело в том, что для каждой реализации в целом, может получиться так, что реализация ноды будет чем-то отличаться, например,
в двусвязном списке ты будешь использовать ноду с двумя дополнительными полями, тогда сегмент не подходит, при этом, если
случится так, что тебе придется менять ноду для какой-то из реализаций, то ты будешь ее менять для всех имплементаций, что
может потащить за собой первое, избыточность ноды для определенных мест ее использования, второе, ошибки выполнения.
*/

//class Segment<T> {
//    public T element;
//    public Segment<T> nextSegment;
//}
