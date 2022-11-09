import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, Resolve, Router, RouterStateSnapshot } from '@angular/router';
import { catchError, Observable, throwError } from 'rxjs';
import {QuizzService} from "../../core/quizz/quizz.service";
import {Quizz} from "../../core/quizz/quizz.models";


@Injectable({
    providedIn: 'root'
})
export class QuizzResolver implements Resolve<any>
{
    /**
     * Constructor
     */
    constructor(private _quizzService: QuizzService,
                private _router: Router
    )
    {
    }

    // -----------------------------------------------------------------------------------------------------
    // @ Public methods
    // -----------------------------------------------------------------------------------------------------

    /**
     * Resolver
     *
     * @param route
     * @param state
     */
    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Quizz[]>
    {

        return this._quizzService.getAll()
            .pipe(
                catchError((error) => {

                    // Log the error
                    console.error(error);

                    // Get the parent url
                    const parentUrl = state.url.split('/').slice(0, -1).join('/');

                    // Navigate to there
                    this._router.navigateByUrl(parentUrl);

                    // Throw an error
                    return throwError(error);
                })
            );
    }
}


