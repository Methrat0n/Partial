# Partial

In Typescript, the Partial type constructor takes
a simple type and return an equivalent type with every property now optional.

```typescript
type Partial<T> = {
    [P in keyof T]?: T[P];
}

interface User {
  readonly name: string
}

type PartialUser = Partial[User]

const noUser: PartialUser = {}
```

It's useful when you need to build an instance from pieces and want to convert to
the complete version of the type afterwards.

Now you can do the same in scala :

```scala
@Partial
final case class User(name: String)

PartialUser(Some("merlin"))
```