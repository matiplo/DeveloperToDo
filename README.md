# DeveloperToDo #
an app utilizing Clean Architecture approach

### Overview ###

DeveloperToDo is an app that enables monitoring of work progress during everyday's app development. One can add a new feature to be completed and specify estimated development time. Existing features can be modified and deleted.

### Architecture ###

The appliation utilizes [Clean Architecture approach.](https://blog.8thlight.com/uncle-bob/2012/08/13/the-clean-architecture.html) It becomes more and more popular and Google considers it [one of possible architectures](https://github.com/googlesamples/android-architecture/tree/todo-mvp-clean/) for Android application nowadays.
DeveloperToDo constists of three layers:
- **Implemenation layer.** Here goes everything framework specific: presentation layer (Android activities, fragments, adapters etc.), storage (here: DBFlow), networking code.
- **Interface adapter layer.** Connection layer containiing presenters for each Android view.
- **Business logic layer.** No framemowrk-specific code, solving given problems. Layer contains interactors (use cases).

### Used libraries ###

