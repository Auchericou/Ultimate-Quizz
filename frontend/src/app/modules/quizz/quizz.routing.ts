import { Route } from '@angular/router';
import { QuizzComponent } from 'app/modules/quizz/quizz.component';
import { QuizzResolver } from "./quizz.resolvers";

export const quizzRoutes: Route[] = [
    {
        path     : '',
        component: QuizzComponent,
        resolve  : {
            quizzs: QuizzResolver,
        },
    }
];
