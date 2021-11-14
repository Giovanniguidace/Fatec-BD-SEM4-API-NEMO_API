CREATE OR REPLACE FUNCTION public.searchcandidate(habilit_experience character varying[])
 RETURNS SETOF integer
 LANGUAGE plpgsql
AS $function$
declare
reg varchar(100);
skill varchar(100);
levelSkill varchar(100);
queryResult integer[];
ids integer[];
ret integer;
begin
	foreach reg  in array habilit_experience
	loop
		skill := split_part(reg, '.', 1);
		levelSkill := upper(split_part(reg, '.', 2));

		queryResult := array(select c.can_id from candidate c
		join candidate_skill cs on cs.fk_can_id = c.can_id
		join skill s on s.skill_id = cs.fk_skill_id
		where s.description = skill and cs."skill_level" = levelSkill::skill_level);
		ids := array_cat(ids, queryResult);
		raise notice 'Value: %, Skill: %, Level: %, %', ids, skill, levelSkill, queryResult ;

end loop ;
	foreach ret in array ids
	loop
		return next ret;
end loop;
	return  ;
end;
$function$
;
